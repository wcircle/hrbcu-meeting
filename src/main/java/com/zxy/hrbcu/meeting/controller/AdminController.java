package com.zxy.hrbcu.meeting.controller;

import com.google.common.collect.Maps;
import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.domain.TMeetingCollege;
import com.zxy.hrbcu.meeting.domain.TMeetingNotice;
import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.domain.TUserReceive;
import com.zxy.hrbcu.meeting.dto.EatDto;
import com.zxy.hrbcu.meeting.dto.NoticeDto;
import com.zxy.hrbcu.meeting.dto.ReceiveDto;
import com.zxy.hrbcu.meeting.dto.SleepDto;
import com.zxy.hrbcu.meeting.service.*;
import com.zxy.hrbcu.meeting.util.MapTools;
import com.zxy.hrbcu.meeting.util.Page;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zxy on 2017/6/26.
 *
 * @author zxy.
 * @version 1.0
 * @since 2018.02.27
 */
@Controller
@RequestMapping("/backAdmin")
@Scope("prototype")
public class AdminController {
    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private MeetingNoticeService meetingNoticeService;

    @Resource
    private ReceiveService receiveService;

    @Resource
    private MeetingMainService meetingService;

    @Resource
    private UserService userService;

    @Resource
    private ExportService exportService;

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView("backAdmin/index");
        Page page = new Page(1, Constant.PER_PAGE_SIZE);
        TMeetingNotice noticeParam = new TMeetingNotice();
        noticeParam.setMainId(Constant.MEETING_MAIN_ID);
        //获取所有有效的通知
        List<Map<String,Object>> meetingNoticeList = meetingNoticeService.getMeetingNoticeList(noticeParam,page);
        mv.addObject("meetingNoticeList",meetingNoticeList);
        mv.addObject("page",page);
        return mv;
    }

    /**
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/getMeetingNoticeList")
    public ModelAndView getMeetingNoticeList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("backAdmin/index");
        int currentPage = 1;
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page page = new Page(currentPage, Constant.PER_PAGE_SIZE);
        TMeetingNotice noticeParam = new TMeetingNotice();
        noticeParam.setMainId(Constant.MEETING_MAIN_ID);
        List<Map<String,Object>> meetingNoticeList = meetingNoticeService.getMeetingNoticeList(noticeParam,page);

        mv.addObject("meetingNoticeList", meetingNoticeList);
        mv.addObject("page",page);
        return mv;
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receive.html", method = RequestMethod.GET)
    public ModelAndView toReceive() {
        ModelAndView mv = new ModelAndView("backAdmin/receiveBackAdmin");
        //获取回执列表
        Page page = new Page(1,Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        List<Map<String,String>> receiveList = receiveService.getReceiveList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("receiveList",receiveList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        return mv;
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/college.html", method = RequestMethod.GET)
    public ModelAndView toCollege() {
        ModelAndView mv = new ModelAndView("backAdmin/collegeBackAdmin");
        //获取学院列表
        Page page = new Page(1,Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        List<Map<String,String>> collegeList = receiveService.getCollegeList(param,page);
        mv.addObject("collegeList",collegeList);
        mv.addObject("page",page);
        return mv;
    }

    @RequestMapping(value = "/getCollegeList")
    public ModelAndView getCollegeList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("backAdmin/collegeBackAdmin");
        //获取学院列表
        int currentPage = 1;
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page page = new Page(currentPage, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        if(!StringUtils.isEmpty(request.getParameter("collegeName"))){
            param.put("collegeName",request.getParameter("collegeName"));
        }
        List<Map<String,String>> collegeList = receiveService.getCollegeList(param,page);
        mv.addObject("collegeList",collegeList);
        mv.addObject("page",page);
        mv.addObject("collegeSearch",param);
        return mv;
    }

    /**
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/getReceiveList")
    public ModelAndView getReceiveList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("backAdmin/receiveBackAdmin");
        //获取回执列表
        int currentPage = 1;
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page page = new Page(currentPage, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        if(!StringUtils.isEmpty(request.getParameter("userName"))){
            param.put("userName",request.getParameter("userName"));
        }
        if(!StringUtils.isEmpty(request.getParameter("phone"))){
            param.put("phone",request.getParameter("phone"));
        }
        if(!StringUtils.isEmpty(request.getParameter("representCollege"))){
            param.put("representCollege",request.getParameter("representCollege"));
        }

        if(!StringUtils.isEmpty(request.getParameter("isSend"))){
            param.put("isSend",request.getParameter("isSend"));
        }

        if(!StringUtils.isEmpty(request.getParameter("bedRequire"))){
            param.put("bedRequire",request.getParameter("bedRequire"));
        }
        List<Map<String,String>> receiveList = receiveService.getReceiveList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("receiveList",receiveList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        mv.addObject("receiveSearch",param);
        return mv;
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/eat.html", method = RequestMethod.GET)
    public ModelAndView toEat() {
        ModelAndView mv = new ModelAndView("backAdmin/eatBackAdmin");
        //获取就餐安排列表
        Page page = new Page(1, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        List<Map<String,String>> eatList = receiveService.getEatList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("eatList",eatList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        return mv;
    }

    /**
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/getEatList")
    public ModelAndView getEatList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("backAdmin/eatBackAdmin");
        //获取就餐列表
        int currentPage = 1;
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page page = new Page(currentPage, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        if(!StringUtils.isEmpty(request.getParameter("userName"))){
            param.put("userName",request.getParameter("userName"));
        }
        if(!StringUtils.isEmpty(request.getParameter("representCollege"))){
            param.put("representCollege",request.getParameter("representCollege"));
        }
        if(!StringUtils.isEmpty(request.getParameter("eatOrder"))){
            param.put("eatOrder",request.getParameter("eatOrder"));
        }
        if(!StringUtils.isEmpty(request.getParameter("address"))){
            param.put("address",request.getParameter("address"));
        }
        if(!StringUtils.isEmpty(request.getParameter("roomNo"))){
            param.put("roomNo",request.getParameter("roomNo"));
        }
        if(!StringUtils.isEmpty(request.getParameter("tableNo"))){
            param.put("tableNo",request.getParameter("tableNo"));
        }

        List<Map<String,String>> eatList = receiveService.getEatList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("eatList",eatList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        mv.addObject("eatSearch",param);
        return mv;
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/sleep.html", method = RequestMethod.GET)
    public ModelAndView toSleep() {
        ModelAndView mv = new ModelAndView("backAdmin/sleepBackAdmin");
        //获取住宿安排列表
        Page page = new Page(1, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        List<Map<String,String>> sleepList = receiveService.getSleepList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("sleepList",sleepList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        return mv;
    }

    @RequestMapping(value = "/getSleepList")
    public ModelAndView getSleepList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("backAdmin/sleepBackAdmin");
        //获取住宿列表
        int currentPage = 1;
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page page = new Page(currentPage, Constant.PER_PAGE_SIZE);
        Map param = Maps.newHashMap();
        if(!StringUtils.isEmpty(request.getParameter("userName"))){
            param.put("userName",request.getParameter("userName"));
        }
        if(!StringUtils.isEmpty(request.getParameter("representCollege"))){
            param.put("representCollege",request.getParameter("representCollege"));
        }
        if(!StringUtils.isEmpty(request.getParameter("sleepOrder"))){
            param.put("sleepOrder",request.getParameter("sleepOrder"));
        }
        if(!StringUtils.isEmpty(request.getParameter("address"))){
            param.put("address",request.getParameter("address"));
        }
        if(!StringUtils.isEmpty(request.getParameter("roomNo"))){
            param.put("roomNo",request.getParameter("roomNo"));
        }
        if(!StringUtils.isEmpty(request.getParameter("sleepDate"))){
            param.put("sleepDate",request.getParameter("sleepDate"));
        }

        List<Map<String,String>> sleepList = receiveService.getSleepList(param,page);
        //获取代表单位列表
        List<TMeetingCollege> collegeList = meetingService.getMeetingCollegeList();

        mv.addObject("sleepList",sleepList);
        mv.addObject("page",page);
        mv.addObject("collegeList",collegeList);
        mv.addObject("sleepSearch",param);
        return mv;
    }


    /**
     * Upload file result vo.
     *
     * @param request  the request
     * @param response the response
     * @return the result vo
     * @throws Exception the exception
     * @since 2018.03.20
     */
    @RequestMapping(value="/uploadFile", method= RequestMethod.POST)
    @ResponseBody
    public ResultVo uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        try {
            ResultVo resultVo = meetingNoticeService.uploadFile(request, response);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController uploadFile error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("上传失败，请稍后重试。");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/noticeAdd.html", method = RequestMethod.GET)
    public ModelAndView toNoticeAdd() {
        ModelAndView mv = new ModelAndView("backAdmin/noticeAdd");
        return mv;
    }


    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/noticeEdit.html/{id}", method = RequestMethod.GET)
    public ModelAndView toNoticeEdit(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("backAdmin/noticeEdit");
        Map<String,Object> resultMap = meetingNoticeService.getMeetngNoticeDetailById(id);
        mv.addObject("resultMap",resultMap);
        return mv;
    }

    @RequestMapping(value="/noticeAdd", method= RequestMethod.POST)
    @ResponseBody
    public ResultVo noticeAdd(NoticeDto noticeDto) {
        try {
            ResultVo resultVo = meetingNoticeService.noticeAdd(noticeDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController noticeAdd error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("添加失败，请稍后重试。");
            return resultVo;
        }
    }

    @RequestMapping(value="/noticeEdit", method= RequestMethod.POST)
    @ResponseBody
    public ResultVo noticeEdit(NoticeDto noticeDto) {
        try {
            ResultVo resultVo = meetingNoticeService.noticeEdit(noticeDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController noticeEdit error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("修改失败，请稍后重试。");
            return resultVo;
        }
    }

    /**
     * 通知批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/noticeDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo noticeDelete(String ids) {
        try {
            ResultVo resultVo = meetingNoticeService.noticeDelete(ids);
            return resultVo;
        } catch (Exception e) {
            logger.info("AdminController noticeDelete error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("批量删除失败");
            return resultVo;
        }
    }

    /**
     * 回执批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/receiveDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo receiveDelete(String ids)  {
        try {
            ResultVo resultVo = receiveService.receiveDelete(ids);
            return resultVo;
        } catch (Exception e) {
            logger.info("AdminController receiveDelete error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("批量删除失败");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receiveAdd.html", method = RequestMethod.GET)
    public ModelAndView toReceiveAdd() {
        ModelAndView mv = new ModelAndView("backAdmin/receiveAdd");
        //获取所有为填写回执的用户
        List<Map<String,String>> userList = receiveService.getNoReceiveUserList();
        //获取代表单位列表
        List<TMeetingCollege> collegeList =meetingService.getMeetingCollegeList();
        mv.addObject("userList",userList);
        mv.addObject("collegeList",collegeList);
        return mv;
    }

    @RequestMapping(value = "/getUserById",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo getUserById(String id) {
        try{
            ResultVo resultVo = new ResultVo();
            TUUser user = userService.getUserById(id);
            Map result = Maps.newHashMap();
            result.put("userName",user.getUserName());
            result.put("idNo",user.getIdNo());
            result.put("gender",user.getGender());
            result.put("phone",user.getPhone());
            resultVo.setData(result);
            resultVo.setStatus(1);
            resultVo.setMsg("获取成功");
            return resultVo;
        } catch (Exception e) {
            logger.info("AdminController getUserById error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("获取失败,请稍后重试");
            return resultVo;
        }
    }

    /**
     * 回执提交
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receiveAdd",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo receiveAdd(ReceiveDto receiveDto){
        ResultVo resultVo = new ResultVo();
        try {
            resultVo = receiveService.receiveAddByAdmin(receiveDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController receiveAdd error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("回执提交失败，请稍后重试");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receiveEdit.html/{receiveId}", method = RequestMethod.GET)
    public ModelAndView toReceiveEdit(@PathVariable String receiveId) {
        ModelAndView mv = new ModelAndView("backAdmin/receiveEdit");
        //获取回执
        TUserReceive receive = receiveService.getReceiveById(receiveId);
        Map receiveMap = MapTools.ConvertObjToMap(receive);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(receive.getArriveTime() != null) {
            receiveMap.put("arriveTime", format.format(receive.getArriveTime()));
        }
        if(receive.getReturnTime() != null) {
            receiveMap.put("returnTime", format.format(receive.getReturnTime()));
        }
        //获取用户信息
        TUUser user = userService.getUserById(receive.getUserId());
        Map result = Maps.newHashMap();
        result.put("userName",user.getUserName());
        result.put("idNo",user.getIdNo());
        result.put("gender",user.getGender());
        result.put("phone",user.getPhone());
        //获取代表单位列表
        List<TMeetingCollege> collegeList =meetingService.getMeetingCollegeList();
        mv.addObject("collegeList",collegeList);
        mv.addObject("receive",receiveMap);
        mv.addObject("user",result);
        return mv;
    }

    /**
     * 回执提交
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receiveEdit",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo receiveEdit(ReceiveDto receiveDto){
        ResultVo resultVo = new ResultVo();
        try {
            resultVo = receiveService.receiveEditByAdmin(receiveDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController receiveEdit error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("回执提交失败，请稍后重试");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/eatAdd.html", method = RequestMethod.GET)
    public ModelAndView toEatAdd() {
        ModelAndView mv = new ModelAndView("backAdmin/eatAdd");
        //获取所有填写回执的用户
        List<Map<String,String>> userList = receiveService.getReceiveUserList();
        mv.addObject("userList",userList);
        return mv;
    }

    @RequestMapping(value = "/eatAdd",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo eatAdd(EatDto eatDto){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.eatAdd(eatDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController eatAdd error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("就餐安排失败，请联系管理员");
            return resultVo;
        }
    }

    /**
     * 就餐批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/eatDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo eatDelete(String ids)  {
        try {
            ResultVo resultVo = receiveService.eatDelete(ids);
            return resultVo;
        } catch (Exception e) {
            logger.info("AdminController eatDelete error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("批量删除失败");
            return resultVo;
        }
    }

    /**
     * 就餐批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sleepDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo sleepDelete(String ids)  {
        try {
            ResultVo resultVo = receiveService.sleepDelete(ids);
            return resultVo;
        } catch (Exception e) {
            logger.info("AdminController sleepDelete error :{}", e);
            e.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("批量删除失败");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/eatEdit.html/{id}", method = RequestMethod.GET)
    public ModelAndView toEatEdit(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("backAdmin/eatEdit");
        //获取就餐信息
        Map<String,String> eat = receiveService.getEatById(id);
        mv.addObject("eat",eat);
        return mv;
    }

    @RequestMapping(value = "/eatEdit",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo eatEdit(EatDto eatDto){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.eatEdit(eatDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController eatEdit error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("就餐安排修改失败，请联系管理员");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/sleepAdd.html", method = RequestMethod.GET)
    public ModelAndView toSleepAdd() {
        ModelAndView mv = new ModelAndView("backAdmin/sleepAdd");
        //获取所有填写回执的用户
        List<Map<String,String>> userList = receiveService.getNeedSleepReceiveUserList();
        mv.addObject("userList",userList);
        return mv;
    }

    @RequestMapping(value = "/sleepAdd",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo sleepAdd(SleepDto sleepDto){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.sleepAdd(sleepDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController sleepAdd error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("就餐安排失败，请联系管理员");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/sleepEdit.html/{id}", method = RequestMethod.GET)
    public ModelAndView toSleepEdit(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("backAdmin/sleepEdit");
        //获取所有填写回执的用户
        Map<String,String> sleep = receiveService.getSleepById(id);
        mv.addObject("sleep",sleep);
        return mv;
    }

    @RequestMapping(value = "/sleepEdit",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo sleepEdit(SleepDto sleepDto){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.sleepEdit(sleepDto);
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController sleepEdit error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("住宿安排修改失败，请联系管理员");
            return resultVo;
        }
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/meetingData.html", method = RequestMethod.GET)
    public ModelAndView toMeetingData() {
        ModelAndView mv = new ModelAndView("backAdmin/meetingDataBackAdmin");
        Map<String,Object> result = receiveService.getStatistics();
        mv.addObject("result",result);
        return mv;
    }

    @RequestMapping(value = "/exportReceiveList",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo exportReceiveList(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try {
            //获取回执列表
            Map param = Maps.newHashMap();
            if (!StringUtils.isEmpty(request.getParameter("userName"))) {
                param.put("userName", request.getParameter("userName"));
            }
            if (!StringUtils.isEmpty(request.getParameter("phone"))) {
                param.put("phone", request.getParameter("phone"));
            }
            if (!StringUtils.isEmpty(request.getParameter("representCollege"))) {
                param.put("representCollege", request.getParameter("representCollege"));
            }

            if (!StringUtils.isEmpty(request.getParameter("isSend"))) {
                param.put("isSend", request.getParameter("isSend"));
            }

            if (!StringUtils.isEmpty(request.getParameter("bedRequire"))) {
                param.put("bedRequire", request.getParameter("bedRequire"));
            }

            if (!StringUtils.isEmpty(request.getParameter("isBedRequire"))) {
                param.put("isBedRequire", request.getParameter("isBedRequire"));
            }
            List<Map<String, String>> receiveList = receiveService.getReceiveListNoPage(param);
            String exportFilePath = exportService.exportExcelForReceive(request,receiveList);

            resultVo.setStatus(1);
            resultVo.setData(exportFilePath);
            resultVo.setMsg("导出成功");
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController exportReceiveList error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("导出失败，请稍后重试");
            return resultVo;
        }

    }

    @RequestMapping(value = "/exportEatList",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo exportEatList(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try {
            Map param = Maps.newHashMap();
            if(!StringUtils.isEmpty(request.getParameter("userName"))){
                param.put("userName",request.getParameter("userName"));
            }
            if(!StringUtils.isEmpty(request.getParameter("representCollege"))){
                param.put("representCollege",request.getParameter("representCollege"));
            }
            if(!StringUtils.isEmpty(request.getParameter("eatOrder"))){
                param.put("eatOrder",request.getParameter("eatOrder"));
            }
            if(!StringUtils.isEmpty(request.getParameter("address"))){
                param.put("address",request.getParameter("address"));
            }
            if(!StringUtils.isEmpty(request.getParameter("roomNo"))){
                param.put("roomNo",request.getParameter("roomNo"));
            }
            if(!StringUtils.isEmpty(request.getParameter("tableNo"))){
                param.put("tableNo",request.getParameter("tableNo"));
            }

            List<Map<String, String>> eatList = receiveService.getEatListNoPage(param);
            String exportFilePath = exportService.exportExcelForEat(request,eatList);

            resultVo.setStatus(1);
            resultVo.setData(exportFilePath);
            resultVo.setMsg("导出成功");
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController exportEatList error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("导出失败，请稍后重试");
            return resultVo;
        }

    }

    @RequestMapping(value = "/exportSleepList",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo exportSleepList(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try {
            Map param = Maps.newHashMap();
            if(!StringUtils.isEmpty(request.getParameter("userName"))){
                param.put("userName",request.getParameter("userName"));
            }
            if(!StringUtils.isEmpty(request.getParameter("representCollege"))){
                param.put("representCollege",request.getParameter("representCollege"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sleepOrder"))){
                param.put("sleepOrder",request.getParameter("sleepOrder"));
            }
            if(!StringUtils.isEmpty(request.getParameter("address"))){
                param.put("address",request.getParameter("address"));
            }
            if(!StringUtils.isEmpty(request.getParameter("roomNo"))){
                param.put("roomNo",request.getParameter("roomNo"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sleepDate"))){
                param.put("sleepDate",request.getParameter("sleepDate"));
            }

            List<Map<String, String>> sleepList = receiveService.getSleepListNoPage(param);
            String exportFilePath = exportService.exportExcelForSleep(request,sleepList);

            resultVo.setStatus(1);
            resultVo.setData(exportFilePath);
            resultVo.setMsg("导出成功");
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController exportSleepList error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("导出失败，请稍后重试");
            return resultVo;
        }

    }

    @RequestMapping(value = "/exportUserList",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo exportUserList(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try {
            List<Map<String, String>> userList = userService.getUserListForExcel();
            String exportFilePath = exportService.exportExcelForUser(request,userList);

            resultVo.setStatus(1);
            resultVo.setData(exportFilePath);
            resultVo.setMsg("导出成功");
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController exportUserList error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("导出失败，请稍后重试");
            return resultVo;
        }

    }

    @RequestMapping(value = "/exportCollegeStatistics",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo exportCollegeStatistics(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try {
            List<Map<String, String>> statisticsList = receiveService.getCollegeStatistics();
            String exportFilePath = exportService.exportExcelForCollegeStatistics(request,statisticsList);

            resultVo.setStatus(1);
            resultVo.setData(exportFilePath);
            resultVo.setMsg("导出成功");
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController exportCollegeStatistics error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("导出失败，请稍后重试");
            return resultVo;
        }

    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/collegeAdd.html", method = RequestMethod.GET)
    public ModelAndView toCollegeAdd() {
        ModelAndView mv = new ModelAndView("backAdmin/collegeAdd");
        return mv;
    }

    @RequestMapping(value = "/collegeAdd",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo collegeAdd(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.collegeAdd(request.getParameter("name"));
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController collegeAdd error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("学院添加失败，请联系管理员");
            return resultVo;
        }
    }

    @RequestMapping(value = "/collegeEdit.html/{id}", method = RequestMethod.GET)
    public ModelAndView toCollegeEdit(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("backAdmin/collegeEdit");
        TMeetingCollege college = receiveService.getCollegeById(id);
        mv.addObject("college",college);
        return mv;
    }

    @RequestMapping(value = "/collegeEdit",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo collegeEdit(HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
        try{
            resultVo = receiveService.collegeEdit(request.getParameter("id"),request.getParameter("name"));
            return resultVo;
        }catch (Exception e){
            logger.info("AdminController collegeAdd error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("学院添加失败，请联系管理员");
            return resultVo;
        }
    }

}
