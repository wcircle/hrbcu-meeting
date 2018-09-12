package com.zxy.hrbcu.meeting.controller;

import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.domain.TMeetingNotice;
import com.zxy.hrbcu.meeting.dto.RegisterDto;
import com.zxy.hrbcu.meeting.service.MeetingNoticeService;
import com.zxy.hrbcu.meeting.service.UserService;
import com.zxy.hrbcu.meeting.util.Page;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/index")
@Scope("prototype")
public class IndexController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private MeetingNoticeService meetingNoticeService;

    @Resource
    private UserService userService;

    /**
     * To index model and view.
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView("indexs/index");
        TMeetingNotice noticeParam = new TMeetingNotice();
        noticeParam.setMainId(Constant.MEETING_MAIN_ID);
        Page page = new Page(1, Constant.PER_PAGE_SIZE);
        List<Map<String,Object>> meetingNoticeList = meetingNoticeService.getMeetingNoticeList(noticeParam,page);
        mv.addObject("meetingNoticeList", meetingNoticeList);
        mv.addObject("page",page);
        return mv;
    }

    /**
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/getMeetingNoticeList",method = RequestMethod.POST)
    public ModelAndView getMeetingNoticeList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("indexs/index");
        Page page = new Page(Integer.parseInt(request.getParameter("page")), Constant.PER_PAGE_SIZE);
        TMeetingNotice noticeParam = new TMeetingNotice();
        noticeParam.setMainId(Constant.MEETING_MAIN_ID);
        List<Map<String,Object>> meetingNoticeList = meetingNoticeService.getMeetingNoticeList(noticeParam,page);

        mv.addObject("meetingNoticeList", meetingNoticeList);
        mv.addObject("page",page);
        return mv;
    }

    /**
     * 通知详情
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/noticeDetail.html/{id}", method = RequestMethod.GET)
    public ModelAndView toNoticeDetail(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("indexs/detail");
        Map<String,Object> meetingNoticeResult = meetingNoticeService.getMeetngNoticeDetailById(id);
        mv.addObject("meetingNoticeResult", meetingNoticeResult);
        return mv;
    }

    /**
     * 注册页面
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/register.html", method = RequestMethod.GET)
    public ModelAndView toRegister() {
        ModelAndView mv = new ModelAndView("indexs/register");
        return mv;
    }

    /**
     * 注册页面
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/noLogin.html", method = RequestMethod.GET)
    public ModelAndView toNoLogin() {
        ModelAndView mv = new ModelAndView("indexs/noLogin");
        return mv;
    }

    /**
     * 注册
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo register(RegisterDto registerDto){
        ResultVo resultVo = new ResultVo();
        try {
            resultVo = userService.register(registerDto);
            return resultVo;
        }catch (Exception e){
            logger.info("IndexController register error :{}", e);
            e.printStackTrace();
            resultVo.setStatus(0);
            resultVo.setMsg("注册失败，请稍后重试");
            return resultVo;
        }
    }

}
