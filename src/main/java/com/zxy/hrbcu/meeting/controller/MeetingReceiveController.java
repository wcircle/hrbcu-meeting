package com.zxy.hrbcu.meeting.controller;

import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.domain.TMeetingCollege;
import com.zxy.hrbcu.meeting.domain.TMeetingMain;
import com.zxy.hrbcu.meeting.domain.TUserReceive;
import com.zxy.hrbcu.meeting.dto.ReceiveDto;
import com.zxy.hrbcu.meeting.service.MeetingMainService;
import com.zxy.hrbcu.meeting.service.ReceiveService;
import com.zxy.hrbcu.meeting.util.MapTools;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
@RequestMapping("/receiveManage")
@Scope("prototype")
public class MeetingReceiveController {

    @Resource
    private MeetingMainService meetingService;

    @Resource
    private ReceiveService receiveService;

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     *
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receive.html", method = RequestMethod.GET)
    public ModelAndView toReceive() {
        ModelAndView mv = new ModelAndView("manage/receive/receive");
        //获取会议名称
        TMeetingMain meeting = meetingService.getMeetngById(Constant.MEETING_MAIN_ID);
        //获取代表单位列表
        List<TMeetingCollege> collegeList =meetingService.getMeetingCollegeList();
        //获取回执
        TUserReceive receive = receiveService.getReceiveByUserIdAndMeetingId();
        Map receiveMap = MapTools.ConvertObjToMap(receive);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(receive.getArriveTime() != null) {
            receiveMap.put("arriveTime", format.format(receive.getArriveTime()));
        }
        if(receive.getReturnTime() != null) {
            receiveMap.put("returnTime", format.format(receive.getReturnTime()));
        }
        mv.addObject("meeting",meeting);
        mv.addObject("collegeList",collegeList);
        mv.addObject("receive",receiveMap);
        return mv;
    }

    /**
     * 回执提交
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/receiveSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo receiveSubmit(ReceiveDto receiveDto){
        ResultVo resultVo = new ResultVo();
        try {
            resultVo = receiveService.receiveSubmit(receiveDto);
            return resultVo;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("MeetingReceiveController receiveSubmit error :{}", e);
            resultVo.setStatus(0);
            resultVo.setMsg("回执提交失败，请稍后重试");
            return resultVo;
        }
    }





}
