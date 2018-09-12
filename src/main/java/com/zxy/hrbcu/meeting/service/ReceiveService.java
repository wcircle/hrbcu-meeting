package com.zxy.hrbcu.meeting.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.dao.*;
import com.zxy.hrbcu.meeting.domain.*;
import com.zxy.hrbcu.meeting.dto.EatDto;
import com.zxy.hrbcu.meeting.dto.ReceiveDto;
import com.zxy.hrbcu.meeting.dto.SleepDto;
import com.zxy.hrbcu.meeting.util.*;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class ReceiveService {

    @Resource
    private TUserReceiveDao receiveDao;

    @Resource
    private TUserEatingDao eatingDao;

    @Resource
    private TUserSleepingDao sleepingDao;

    @Resource
    private UserService userService;

    @Resource
    private TUUserDao userDao;

    @Resource
    private TMeetingCollegeDao collegeDao;

    /**
     * 获取回执信息
     * @return
     */
    public TUserReceive getReceiveByUserIdAndMeetingId(){
        String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
        String meetingId = Constant.MEETING_MAIN_ID;
        TUserReceive receive = new TUserReceive();
        TUserReceive param = new TUserReceive();
        param.setUserId(userId);
        param.setMeetingId(meetingId);
        List<TUserReceive> receiveList = receiveDao.selectBySelective(param);
        if(CollectionUtils.isEmpty(receiveList)){
            return receive;
        }
        return receiveList.get(0);
    }

    /**
     * 获取管理端回执信息
     * @return
     */
    public TUserReceive getReceiveById(String receiveId){
        TUserReceive receive = receiveDao.selectByPrimaryKey(receiveId);
        return receive;
    }

    /**
     * 提交回执
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo receiveSubmit(ReceiveDto receiveDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Receive(receiveDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        //判断是新增，还是更新
        if(StringUtils.isEmpty(receiveDto.getId())){
            String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
            //判断是否已存在
            TUserReceive param = new TUserReceive();
            param.setUserId(userId);
            param.setMeetingId(Constant.MEETING_MAIN_ID);
            List<TUserReceive> receiveList = receiveDao.selectBySelective(param);
            if(!CollectionUtils.isEmpty(receiveList)){
                resultVo.setStatus(0);
                resultVo.setMsg("提交回执失败，请稍后重试。");
                return resultVo;
            }
            //新增
            addReceive(receiveDto,userId);
        }else{
            //更新
            updateReceive(receiveDto);
        }
        resultVo.setStatus(1);
        resultVo.setMsg("回执提交成功");
        return resultVo;
    }

    /**
     * 管理端添加回执
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo receiveAddByAdmin(ReceiveDto receiveDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Receive(receiveDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }
        //校验用户标识
        if(StringUtils.isEmpty(receiveDto.getUserId())){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择用户");
            return resultVo;
        }
        TUUser user = userService.getUserById(receiveDto.getUserId());
        if(user == null){
            resultVo.setStatus(0);
            resultVo.setMsg("此用户不存在，请重新选择");
            return resultVo;
        }

        //添加回执
        addReceive(receiveDto,receiveDto.getUserId());

        resultVo.setStatus(1);
        resultVo.setMsg("回执提交成功");
        return resultVo;
    }

    /**
     * 管理端修改回执
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo receiveEditByAdmin(ReceiveDto receiveDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Receive(receiveDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        //判断回执是否存在
        if(StringUtils.isEmpty(receiveDto.getId())){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择修改用户");
            return resultVo;
        }
        TUserReceive receive = receiveDao.selectByPrimaryKey(receiveDto.getId());
        if(receive == null || receive.getState() == Constant.STATE_DELETE){
            resultVo.setStatus(0);
            resultVo.setMsg("回执不存在，请刷新后重试。");
            return resultVo;
        }

        //更新
        updateReceive(receiveDto);
        resultVo.setStatus(1);
        resultVo.setMsg("回执修改成功");
        return resultVo;
    }

    /**
     * 添加回执
     * @param receiveDto
     * @param userId
     */
    @Transactional(rollbackFor = {Exception.class})
    public void addReceive(ReceiveDto receiveDto,String userId) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String meetingId = Constant.MEETING_MAIN_ID;
        TUserReceive receive = new TUserReceive();
        Assembler.assemble(receiveDto,receive);
        receive.setId(UUIDTool.getUUID());
        receive.setUserId(userId);
        receive.setMeetingId(meetingId);
        if(!StringUtils.isEmpty(receiveDto.getArriveTime())) {
            receive.setArriveTime(sdf.parse(receiveDto.getArriveTime()));
        }else{
            receive.setArriveTime(null);
        }
        if(!StringUtils.isEmpty(receiveDto.getReturnTime())) {
            receive.setReturnTime(sdf.parse(receiveDto.getReturnTime()));
        }else{
            receive.setReturnTime(null);
        }
        if(StringUtils.isEmpty(receiveDto.getIsPrivate())){
            receive.setIsPrivate(Constant.NO_CHECKED);
        }
        if(StringUtils.isEmpty(receiveDto.getIsSend())){
            receive.setIsSend(Constant.NO_CHECKED);
            receive.setArriveType("");
            receive.setArriveFlightNo("");
            receive.setArriveTime(null);
            receive.setArriveAirport("");
            receive.setReturnType("");
            receive.setReturnFlightNo("");
            receive.setReturnTime(null);
            receive.setReturnAirport("");

        }
        receive.setCreateStaffId(userId);
        receive.setCreateTime(new Date());
        receive.setUpdateStaffId(userId);
        receive.setUpdateTime(new Date());
        receive.setState(Constant.STATE_AVAILABLE);
        receiveDao.insertSelective(receive);
    }

    /**
     * 更新回执
     * @param receiveDto
     */
    @Transactional(rollbackFor = {Exception.class})
    public void updateReceive(ReceiveDto receiveDto) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginUserId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
        TUserReceive receive = receiveDao.selectByPrimaryKey(receiveDto.getId());
        Assembler.assemble(receiveDto,receive);
        if(!StringUtils.isEmpty(receiveDto.getArriveTime())) {
            receive.setArriveTime(sdf.parse(receiveDto.getArriveTime()));
        }else{
            receive.setArriveTime(null);
        }
        if(!StringUtils.isEmpty(receiveDto.getReturnTime())) {
            receive.setReturnTime(sdf.parse(receiveDto.getReturnTime()));
        }else{
            receive.setReturnTime(null);
        }
        if(StringUtils.isEmpty(receiveDto.getIsPrivate())){
            receive.setIsPrivate(Constant.NO_CHECKED);
        }
        if(StringUtils.isEmpty(receiveDto.getIsSend())){
            receive.setIsSend(Constant.NO_CHECKED);
            receive.setArriveType("");
            receive.setArriveFlightNo("");
            receive.setArriveTime(null);
            receive.setArriveAirport("");
            receive.setReturnType("");
            receive.setReturnFlightNo("");
            receive.setReturnTime(null);
            receive.setReturnAirport("");
        }
        receive.setUpdateStaffId(loginUserId);
        receive.setUpdateTime(new Date());
        receiveDao.updateByPrimaryKeySelective(receive);
    }

    /**
     * 获取回执列表
     * @param
     * @return
     */
    public List<Map<String,String>> getReceiveList(Map param, Page page){
        List<Map<String,String>> receiveList = Lists.newArrayList();
        PageHelper.startPage(page.getCurrentPageNum(), page.getPerPageSize());
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        receiveList = receiveDao.getReceiveList(param);
        // 取分页信息
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(receiveList);
        // 获取总记录数
        page.setTotalCount(NumberUtils.toInt(pageInfo.getTotal() + ""));
        return receiveList;
    }

    /**
     * 获取学院列表
     * @param
     * @return
     */
    public List<Map<String,String>> getCollegeList(Map param, Page page){
        List<Map<String,String>> collegeList = Lists.newArrayList();
        PageHelper.startPage(page.getCurrentPageNum(), page.getPerPageSize());
        param.put("collegeName",param.get("collegeName"));
        collegeList = receiveDao.getCollegeList(param);
        // 取分页信息
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(collegeList);
        // 获取总记录数
        page.setTotalCount(NumberUtils.toInt(pageInfo.getTotal() + ""));
        return collegeList;
    }

    /**
     * 获取回执列表
     * @param
     * @return
     */
    public List<Map<String,String>> getReceiveListNoPage(Map param){
        List<Map<String,String>> receiveList = Lists.newArrayList();
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        receiveList = receiveDao.getReceiveList(param);
        return receiveList;
    }

    /**
     * 获取就餐列表
     * @param
     * @return
     */
    public List<Map<String,String>> getEatList(Map param, Page page){
        List<Map<String,String>> eatList = Lists.newArrayList();
        PageHelper.startPage(page.getCurrentPageNum(), page.getPerPageSize());
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        eatList = eatingDao.getEatList(param);
        // 取分页信息
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(eatList);
        // 获取总记录数
        page.setTotalCount(NumberUtils.toInt(pageInfo.getTotal() + ""));
        return eatList;
    }

    /**
     * 获取就餐列表
     * @param
     * @return
     */
    public List<Map<String,String>> getEatListNoPage(Map param){
        List<Map<String,String>> eatList = Lists.newArrayList();
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        eatList = eatingDao.getEatList(param);
        return eatList;
    }

    /**
     * 获取住宿列表
     * @param
     * @return
     */
    public List<Map<String,String>> getSleepList(Map param, Page page){
        List<Map<String,String>> sleepList = Lists.newArrayList();
        PageHelper.startPage(page.getCurrentPageNum(), page.getPerPageSize());
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        sleepList = sleepingDao.getSleepList(param);
        // 取分页信息
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(sleepList);
        // 获取总记录数
        page.setTotalCount(NumberUtils.toInt(pageInfo.getTotal() + ""));
        return sleepList;
    }

    /**
     * 获取住宿列表
     * @param
     * @return
     */
    public List<Map<String,String>> getSleepListNoPage(Map param){
        List<Map<String,String>> sleepList = Lists.newArrayList();
        param.put("userType",Constant.USER_TYPE_JOIN);
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        sleepList = sleepingDao.getSleepList(param);
        return sleepList;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo receiveDelete(String ids){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(ids)){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择删除的回执。");
            return resultVo;
        }

        List idList = Lists.newArrayList();
        String[] idArray = ids.split(",");
        for(int i = 0 ; i<idArray.length;i++){
            idList.add(idArray[i]);
            //验证是否已安排就餐
            TUserEating eating = new TUserEating();
            eating.setReceiveId(idArray[i]);
            List<TUserEating> eatingList = eatingDao.selectBySelective(eating);
            if(!CollectionUtils.isEmpty(eatingList)){
                resultVo.setStatus(0);
                resultVo.setMsg("存在已安排就餐的人员，请先删除就餐信息，再删除回执");
                return resultVo;
            }

            //验证是否已安排住宿
            TUserSleeping sleeping = new TUserSleeping();
            sleeping.setReceiveId(idArray[i]);
            List<TUserSleeping> sleepingList = sleepingDao.selectBySelective(sleeping);
            if(!CollectionUtils.isEmpty(sleepingList)){
                resultVo.setStatus(0);
                resultVo.setMsg("存在已安排住宿的人员，请先删除就餐信息，再删除回执");
                return resultVo;
            }

        }

        //更新
        HashMap param = Maps.newHashMap();
        param.put("idList",idList);
        param.put("changeState",Constant.STATE_DELETE);
        receiveDao.updateState(param);

        resultVo.setStatus(1);
        resultVo.setMsg("删除成功");
        return resultVo;
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResultVo eatAdd(EatDto eatDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Eat(eatDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        if(EmptyUtils.isEmpty(eatDto.getSelectedReceiveId())){
            resultVo.setStatus(0);
            resultVo.setMsg("就餐用户必选");
            return resultVo;
        }

        for(int i = 0;i<eatDto.getSelectedReceiveId().length;i++) {
            //获取回执信息
            TUserReceive receive = receiveDao.selectByPrimaryKey(eatDto.getSelectedReceiveId()[i]);
            if(receive == null){
                resultVo.setStatus(0);
                resultVo.setMsg("用户不存在，请刷新后重试。");
                return resultVo;
            }
            //添加就餐信息
            String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
            Date nowDate = new Date();
            TUserEating eating = new TUserEating();
            eating.setId(UUIDTool.getUUID());
            eating.setUserId(receive.getUserId());
            eating.setReceiveId(receive.getId());
            eating.setAddress(eatDto.getAddress());
            eating.setRoomNo(eatDto.getRoomNo());
            eating.setTableNo(eatDto.getTableNo());
            eating.setEatOrder(eatDto.getEatOrder());
            eating.setCreateStaffId(userId);
            eating.setCreateTime(nowDate);
            eating.setUpdateStaffId(userId);
            eating.setUpdateTime(nowDate);
            eatingDao.insertSelective(eating);
        }

        resultVo.setStatus(1);
        resultVo.setMsg("添加就餐安排成功。");
        return resultVo;
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResultVo sleepAdd(SleepDto sleepDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Sleep(sleepDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        if(EmptyUtils.isEmpty(sleepDto.getReceiveId())){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿用户必选");
            return resultVo;
        }

        //获取回执信息
        TUserReceive receive = receiveDao.selectByPrimaryKey(sleepDto.getReceiveId());
        if(receive == null){
            resultVo.setStatus(0);
            resultVo.setMsg("用户不存在，请刷新后重试。");
            return resultVo;
        }

        //添加住宿信息
        String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
        Date nowDate = new Date();
        TUserSleeping sleeping = new TUserSleeping();
        sleeping.setId(UUIDTool.getUUID());
        sleeping.setReceiveId(receive.getId());
        sleeping.setUserId(receive.getUserId());
        sleeping.setAddress(sleepDto.getAddress());
        sleeping.setRoomNo(sleepDto.getRoomNo());
        sleeping.setSleepOrder(sleepDto.getSleepOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sleeping.setSleepDate(sdf.parse(sleepDto.getSleepDate()+" 00:00:00"));
        sleeping.setCreateStaffId(userId);
        sleeping.setCreateTime(nowDate);
        sleeping.setUpdateStaffId(userId);
        sleeping.setUpdateTime(nowDate);
        sleepingDao.insertSelective(sleeping);

        resultVo.setStatus(1);
        resultVo.setMsg("添加住宿安排成功。");
        return resultVo;
    }


    private ResultVo validate4Eat(EatDto eatDto){
        ResultVo resultVo = new ResultVo();

        if(eatDto.getEatOrder() == null){
            resultVo.setStatus(0);
            resultVo.setMsg("就餐序号必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(eatDto.getAddress())){
            resultVo.setStatus(0);
            resultVo.setMsg("就餐地址必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(eatDto.getRoomNo())){
            resultVo.setStatus(0);
            resultVo.setMsg("就餐房间号必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(eatDto.getTableNo())){
            resultVo.setStatus(0);
            resultVo.setMsg("就餐桌号必填");
            return resultVo;
        }
        resultVo.setStatus(1);
        return resultVo;
    }

    private ResultVo validate4Sleep(SleepDto sleepDto){
        ResultVo resultVo = new ResultVo();

        if(sleepDto.getSleepOrder() == null){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿序号必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(sleepDto.getAddress())){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿地址必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(sleepDto.getRoomNo())){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿房间号必填");
            return resultVo;
        }
        if(StringUtils.isEmpty(sleepDto.getSleepDate())){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿时间必填");
            return resultVo;
        }
        resultVo.setStatus(1);
        return resultVo;
    }

    private ResultVo validate4Receive(ReceiveDto receiveDto){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(receiveDto.getBedRequire())){
            resultVo.setStatus(0);
            resultVo.setMsg("住宿要求必填");
            return resultVo;
        }

        if(StringUtils.isEmpty(receiveDto.getRepresentCollege())){
            resultVo.setStatus(0);
            resultVo.setMsg("代表单位必填");
            return resultVo;
        }

        if(StringUtils.isEmpty(receiveDto.getWork())){
            resultVo.setStatus(0);
            resultVo.setMsg("职务必填");
            return resultVo;
        }

        resultVo.setStatus(1);
        return resultVo;
    }

    public List<Map<String,String>> getNoReceiveUserList(){
        Map param = Maps.newHashMap();
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        List<Map<String,String>> userList = receiveDao.getNoReceiveUserList(param);
        return userList;
    }

    public List<Map<String,String>> getReceiveUserList(){
        Map param = Maps.newHashMap();
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        List<Map<String,String>> userList = receiveDao.getReceiveUserList(param);
        return userList;
    }

    public List<Map<String,String>> getNeedSleepReceiveUserList(){
        Map param = Maps.newHashMap();
        param.put("meetingId",Constant.MEETING_MAIN_ID);
        param.put("noBedRequire",Constant.bed_require_no);
        List<Map<String,String>> userList = receiveDao.getNeedSleepReceiveUserList(param);
        return userList;
    }

    public List<Map<String,String>> getCollegeStatistics(){
        List<Map<String,String>> statisticsList = receiveDao.getCollegeStatistics();
        return statisticsList;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo eatDelete(String ids){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(ids)){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择删除的就餐安排。");
            return resultVo;
        }

        List idList = Lists.newArrayList();
        String[] idArray = ids.split(",");
        for(int i = 0 ; i<idArray.length;i++){
            idList.add(idArray[i]);
        }

        //删除
        HashMap param = Maps.newHashMap();
        param.put("idList",idList);
        eatingDao.deleteByIds(param);

        resultVo.setStatus(1);
        resultVo.setMsg("删除成功");
        return resultVo;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo sleepDelete(String ids){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(ids)){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择需要删除的住宿安排。");
            return resultVo;
        }

        List idList = Lists.newArrayList();
        String[] idArray = ids.split(",");
        for(int i = 0 ; i<idArray.length;i++){
            idList.add(idArray[i]);
        }

        //删除
        HashMap param = Maps.newHashMap();
        param.put("idList",idList);
        sleepingDao.deleteByIds(param);

        resultVo.setStatus(1);
        resultVo.setMsg("删除成功");
        return resultVo;
    }

    /**
     * 获取就餐信息
     * @param id
     * @return
     */
    public Map<String,String> getEatById(String id){
        Map<String,String> result = Maps.newHashMap();
        TUserEating eating = eatingDao.selectByPrimaryKey(id);
        TUUser user = userService.getUserById(eating.getUserId());
        result.put("id",eating.getId());
        result.put("eatOrder",String.valueOf(eating.getEatOrder()));
        result.put("address",eating.getAddress());
        result.put("roomNo",eating.getRoomNo());
        result.put("tableNo",eating.getTableNo());
        result.put("userName",user.getUserName());
        result.put("phone",user.getPhone());
        return result;

    }

    /**
     * 获取住宿信息
     * @param id
     * @return
     */
    public Map<String,String> getSleepById(String id){
        Map<String,String> result = Maps.newHashMap();
        TUserSleeping sleeping = sleepingDao.selectByPrimaryKey(id);
        TUUser user = userService.getUserById(sleeping.getUserId());
        result.put("id",sleeping.getId());
        result.put("sleepOrder",String.valueOf(sleeping.getSleepOrder()));
        result.put("address",sleeping.getAddress());
        result.put("roomNo",sleeping.getRoomNo());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.put("sleepDate",sdf.format(sleeping.getSleepDate()).substring(0,10));
        result.put("userName",user.getUserName());
        result.put("phone",user.getPhone());
        return result;

    }

    @Transactional(rollbackFor = {Exception.class})
    public ResultVo eatEdit(EatDto eatDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Eat(eatDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        //校验就餐是否存在
        if(StringUtils.isEmpty(eatDto.getId())){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择需要修改的就餐");
            return resultVo;
        }

        TUserEating eating = eatingDao.selectByPrimaryKey(eatDto.getId());
        if(eating == null){
            resultVo.setStatus(0);
            resultVo.setMsg("此就餐信息不存在，请刷新后重试");
            return resultVo;
        }

        //修改就餐信息
        String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
        Date nowDate = new Date();
        eating.setAddress(eatDto.getAddress());
        eating.setRoomNo(eatDto.getRoomNo());
        eating.setTableNo(eatDto.getTableNo());
        eating.setEatOrder(eatDto.getEatOrder());
        eating.setUpdateStaffId(userId);
        eating.setUpdateTime(nowDate);
        eatingDao.updateByPrimaryKeySelective(eating);

        resultVo.setStatus(1);
        resultVo.setMsg("修改就餐安排成功。");
        return resultVo;
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResultVo sleepEdit(SleepDto sleepDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResultVo = validate4Sleep(sleepDto);
        if(validateResultVo.getStatus() == 0){
            return validateResultVo;
        }

        //校验就餐是否存在
        if(StringUtils.isEmpty(sleepDto.getId())){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择需要修改的住宿");
            return resultVo;
        }

        TUserSleeping sleeping = sleepingDao.selectByPrimaryKey(sleepDto.getId());
        if(sleeping == null){
            resultVo.setStatus(0);
            resultVo.setMsg("此住宿信息不存在，请刷新后重试");
            return resultVo;
        }

        //修改就餐信息
        String userId = (String)SpringSessionWebUtil.getSessionAttribute("token_id");
        Date nowDate = new Date();
        sleeping.setAddress(sleepDto.getAddress());
        sleeping.setRoomNo(sleepDto.getRoomNo());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sleeping.setSleepDate(sdf.parse(sleepDto.getSleepDate()+" 00:00:00"));
        sleeping.setSleepOrder(sleepDto.getSleepOrder());
        sleeping.setUpdateStaffId(userId);
        sleeping.setUpdateTime(nowDate);
        sleepingDao.updateByPrimaryKeySelective(sleeping);

        resultVo.setStatus(1);
        resultVo.setMsg("修改住宿安排成功。");
        return resultVo;
    }

    /**
     * 统计数据
     * @return
     */
    public Map<String,Object> getStatistics(){
        Map<String,Object> result = Maps.newHashMap();
        //需要参会单位
        List<TMeetingCollege> collegeList = collegeDao.selectBySelective(new TMeetingCollege());
        result.put("collegeCount",collegeList.size());

        //已回执的单位
        Integer receiveCollegeCount = receiveDao.getReceiveCollegeCount();
        result.put("receiveCollegeCount",receiveCollegeCount);

        //未回执的单位
        result.put("noReceiveCollegeCount",collegeList.size()-receiveCollegeCount);

        //总注册人数
        TUUser param = new TUUser();
        param.setUserType(Constant.USER_TYPE_JOIN);
        List<TUUser> userList = userDao.selectBySelective(param);
        result.put("totalRegisterCount",userList.size());

        //总回执人数
        TUserReceive receiveParam = new TUserReceive();
        receiveParam.setState(Constant.STATE_AVAILABLE);
        List<TUserReceive> receiveList = receiveDao.selectBySelective(receiveParam);
        result.put("totalReceiveCount",receiveList.size());

        //今日注册人数
        Integer todayRegisterCount = userDao.getTodayRegisterUserCount();
        result.put("todayRegisterCount",todayRegisterCount);
        //今日新增回执
        Integer todayCreateReceiveCount = receiveDao.getTodayCreateReceiveCount();
        result.put("todayCreateReceiveCount",todayCreateReceiveCount);

        return result;

    }

    public ResultVo collegeAdd(String name){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(name)){
            resultVo.setStatus(0);
            resultVo.setMsg("请输入学院名称");
            return resultVo;
        }

        TMeetingCollege college = new TMeetingCollege();
        college.setName(name);
        college.setId(UUIDTool.getUUID());
        collegeDao.insertSelective(college);
        resultVo.setStatus(1);
        resultVo.setMsg("添加成功");
        return resultVo;
    }

    public TMeetingCollege getCollegeById(String id){
        return collegeDao.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResultVo collegeEdit(String id,String name) throws Exception{
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(id)){
            resultVo.setStatus(0);
            resultVo.setMsg("请选择学院");
            return resultVo;
        }

        if(StringUtils.isEmpty(name)){
            resultVo.setStatus(0);
            resultVo.setMsg("请输入学院名称");
            return resultVo;
        }

        //获取原院校
        TMeetingCollege college = collegeDao.selectByPrimaryKey(id);
        String oldName = college.getName();
        college.setId(id);
        college.setName(name);
        collegeDao.updateByPrimaryKeySelective(college);
        //获取已填写回执的记录
        TUserReceive param = new TUserReceive();
        param.setRepresentCollege(oldName);
        List<TUserReceive> receiveList = receiveDao.selectBySelective(param);
        //更新回执
        if(!CollectionUtils.isEmpty(receiveList)){
            HashMap updateParam = Maps.newHashMap();
            updateParam.put("oldName",oldName);
            updateParam.put("newName",name);
            receiveDao.updateRepresentCollege(updateParam);
        }

        resultVo.setStatus(1);
        resultVo.setMsg("修改成功");
        return resultVo;
    }

}
