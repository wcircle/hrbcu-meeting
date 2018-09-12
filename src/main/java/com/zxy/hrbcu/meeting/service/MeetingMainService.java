package com.zxy.hrbcu.meeting.service;

import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.dao.TMeetingCollegeDao;
import com.zxy.hrbcu.meeting.dao.TMeetingMainDao;
import com.zxy.hrbcu.meeting.dao.TUserReceiveDao;
import com.zxy.hrbcu.meeting.domain.TMeetingCollege;
import com.zxy.hrbcu.meeting.domain.TMeetingMain;
import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.domain.TUserReceive;
import com.zxy.hrbcu.meeting.util.SpringSessionWebUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class MeetingMainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TMeetingMainDao meetingDao ;

    @Resource
    private TMeetingCollegeDao meetingCollegeDao;

    @Resource
    private TUserReceiveDao receiveDao;

    /**
     * 获取会议名称
     * @param id
     * @return
     */
    public TMeetingMain getMeetngById(String id){
        TMeetingMain meeting = meetingDao.selectByPrimaryKey(id);
        return meeting;

    }

    /**
     * 获取代表单位列表
     * @return
     */
    public List<TMeetingCollege> getMeetingCollegeList(){
        return meetingCollegeDao.selectBySelective(new TMeetingCollege());
    }

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



}
