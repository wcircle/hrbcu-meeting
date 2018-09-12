package com.zxy.hrbcu.meeting.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.dao.TMeetingNoticeDao;
import com.zxy.hrbcu.meeting.dao.TMeetingNoticeFileDao;
import com.zxy.hrbcu.meeting.domain.TMeetingNotice;
import com.zxy.hrbcu.meeting.domain.TMeetingNoticeFile;
import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.dto.NoticeDto;
import com.zxy.hrbcu.meeting.util.*;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class MeetingNoticeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TMeetingNoticeDao meetingNoticeDao ;

    @Resource
    private TMeetingNoticeFileDao meetingNoticeFileDao;

    @Resource
    private UserService userService;

    /**
     * 获取列表
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getMeetingNoticeList(TMeetingNotice noticeParam,Page page){
        List<TMeetingNotice> meetingNoticeList = Lists.newArrayList();
        PageHelper.startPage(page.getCurrentPageNum(), page.getPerPageSize());
        noticeParam.setState(Constant.STATE_AVAILABLE);
        meetingNoticeList = meetingNoticeDao.selectBySelective(noticeParam);
        // 取分页信息
        PageInfo<TMeetingNotice> pageInfo = new PageInfo<TMeetingNotice>(meetingNoticeList);
        // 获取总记录数
        page.setTotalCount(NumberUtils.toInt(pageInfo.getTotal() + ""));
        List<Map<String,Object>> resultList = Lists.newArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(TMeetingNotice notice : meetingNoticeList){
            Map<String,Object> result = MapTools.ConvertObjToMap(notice);
            result.put("createTime",format.format(notice.getCreateTime()).substring(0,10));
            result.put("updateTime",format.format(notice.getUpdateTime()).substring(0,10));
            TUUser createUser = userService.getUserById(notice.getCreateStaffId());
            result.put("createStaffName",createUser.getUserName());
            TUUser updateUser = userService.getUserById(notice.getUpdateStaffId());
            result.put("updateStaffName",updateUser.getUserName());
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * 获取通知详情
     * @param id
     * @return
     */
    public Map<String,Object> getMeetngNoticeDetailById(String id){
        TMeetingNotice meetingNotice = meetingNoticeDao.selectByPrimaryKey(id);
        TMeetingNoticeFile fileParam = new TMeetingNoticeFile();
        fileParam.setNoticeId(id);
        List<TMeetingNoticeFile> fileList = meetingNoticeFileDao.selectBySelective(fileParam);
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("fileList",fileList);
        resultMap.put("meetingNotice",meetingNotice);
        return resultMap;

    }

    public ResultVo uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResultVo resultVo = new ResultVo();
        Iterator<String> iterator = request.getFileNames();
        //遍历所有上传文件
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            String originName = multipartFile.getOriginalFilename();

            //重命名为时间戳
            String originNameNew = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 系统时间
            String originNameNewTemp = originNameNew;
            //获取扩展名
            String extensionName = FilesUtil.getExtensionName(originName);

            originNameNew = originNameNew + "." + extensionName;

            //创建路径 判断文件夹是否存在
            String url = request.getSession().getServletContext().getRealPath("/");
            if (!url.endsWith(File.separator)) {
                url = url + File.separator;
            }
            String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());// 系统时间
            String uploadPath = url + "upload/" + catalog;
            String tempUploadPath = "upload/" + catalog;

            if (!new File(uploadPath).exists()) {
                File dir = new File(uploadPath);
                dir.mkdirs();
            }

            //保存文件
            try {
                // 将接收的文件保存到指定文件中
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(uploadPath, originNameNew));
                Map map = new HashMap<>();

                map.put("id", originNameNewTemp);
                map.put("url", "/"+tempUploadPath + "/" + originNameNew);
                map.put("name", originName);
                resultVo.setData(map);
            } catch (Exception e) {
                resultVo.setStatus(0);
                resultVo.setMsg("上传异常。");
                return resultVo;
            }
        }
        resultVo.setStatus(1);
        resultVo.setMsg("上传成功。");
        return resultVo;
    }


    /**
     * 添加通知
     * @param noticeDto
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo noticeAdd(NoticeDto noticeDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(noticeDto.getTitle())){
            resultVo.setStatus(0);
            resultVo.setMsg("通知标题必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(noticeDto.getContent())){
            resultVo.setStatus(0);
            resultVo.setMsg("通知内容必填");
            return resultVo;
        }

        //添加通知
        String loginUserId = (String) SpringSessionWebUtil.getSessionAttribute("token_id");
        Date nowDate = new Date();
        TMeetingNotice notice = new TMeetingNotice();
        notice.setId(UUIDTool.getUUID());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setState(Constant.STATE_AVAILABLE);
        notice.setMainId(Constant.MEETING_MAIN_ID);
        notice.setCreateStaffId(loginUserId);
        notice.setCreateTime(nowDate);
        notice.setUpdateStaffId(loginUserId);
        notice.setUpdateTime(nowDate);
        meetingNoticeDao.insertSelective(notice);
        //添加附件
        if(!EmptyUtils.isEmpty(noticeDto.getUploadFileIds())){
            noticeFileAdd(notice.getId(),noticeDto);
        }
        return resultVo;
    }

    /**
     * 修改通知
     * @param noticeDto
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo noticeEdit(NoticeDto noticeDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(noticeDto.getId())){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择通知");
            return resultVo;
        }
        if(StringUtils.isEmpty(noticeDto.getTitle())){
            resultVo.setStatus(0);
            resultVo.setMsg("通知标题必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(noticeDto.getContent())){
            resultVo.setStatus(0);
            resultVo.setMsg("通知内容必填");
            return resultVo;
        }
        TMeetingNotice notice = meetingNoticeDao.selectByPrimaryKey(noticeDto.getId());
        if(notice == null || notice.getState() == Constant.STATE_DELETE){
            resultVo.setStatus(0);
            resultVo.setMsg("通知不存在，请刷新后再试。");
            return resultVo;
        }

        //修改通知
        String loginUserId = (String) SpringSessionWebUtil.getSessionAttribute("token_id");
        Date nowDate = new Date();
        notice.setId(noticeDto.getId());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setState(Constant.STATE_AVAILABLE);
        notice.setMainId(Constant.MEETING_MAIN_ID);
        notice.setUpdateStaffId(loginUserId);
        notice.setUpdateTime(nowDate);
        meetingNoticeDao.updateByPrimaryKeySelective(notice);

        //删除相关附件
        noticeFileDelete(noticeDto.getId());
        //添加附件
        if(!EmptyUtils.isEmpty(noticeDto.getUploadFileIds())){
            noticeFileAdd(notice.getId(),noticeDto);
        }
        return resultVo;
    }

    /**
     * 删除附件
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    public void noticeFileDelete(String noticeId) throws Exception {
        //获取结果列表
        TMeetingNoticeFile param = new TMeetingNoticeFile();
        param.setNoticeId(noticeId);
        List<TMeetingNoticeFile> fileList = meetingNoticeFileDao.selectBySelective(param);
        if (!CollectionUtils.isEmpty(fileList) && fileList.size() > 0) {
            //删除
            meetingNoticeFileDao.deleteByCondition(param);
        }

    }

    /**
     * 添加附件
     * @param noticeId
     * @param noticeDto
     */
    @Transactional(rollbackFor = {Exception.class})
    public void noticeFileAdd(String noticeId,NoticeDto noticeDto) throws Exception{
        String[] uploadFileIds = noticeDto.getUploadFileIds();
        String[] uploadFileNames = noticeDto.getUploadFileNames();
        String[] uploadFileUrls = noticeDto.getUploadFileUrls();
        TMeetingNoticeFile noticeFile ;
        //添加附件
        for (int i = 0; i < uploadFileIds.length; i++) {
            noticeFile = new TMeetingNoticeFile();
            noticeFile.setId(UUIDTool.getUUID());
            noticeFile.setNoticeId(noticeId);
            noticeFile.setFileName(uploadFileNames[i]);
            noticeFile.setFilePath(uploadFileUrls[i]);
            meetingNoticeFileDao.insertSelective(noticeFile);
        }
    }

    /**
     * 批量删除通知
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo noticeDelete(String ids){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(ids)){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择删除的通知。");
            return resultVo;
        }
        List idList = Lists.newArrayList();
        String[] idArray = ids.split(",");
        for(int i = 0 ; i<idArray.length;i++){
            idList.add(idArray[i]);
        }

        //更新
        HashMap param = Maps.newHashMap();
        param.put("idList",idList);
        param.put("changeState",Constant.STATE_DELETE);
        meetingNoticeDao.updateState(param);

        resultVo.setStatus(1);
        resultVo.setMsg("删除成功");
        return resultVo;
    }



}
