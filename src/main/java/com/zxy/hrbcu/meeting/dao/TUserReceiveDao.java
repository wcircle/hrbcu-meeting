package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TUserReceive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TUserReceiveDao extends BaseDao<TUserReceive> {
    List<Map<String,String>> getReceiveList(Map param);

    List<Map<String,String>> getCollegeList(Map param);

    int updateState(HashMap map);

    int updateRepresentCollege(HashMap map);
    List<Map<String,String>> getNoReceiveUserList(Map param);

    List<Map<String,String>> getReceiveUserList(Map param);

    List<Map<String,String>> getNeedSleepReceiveUserList(Map param);

    List<Map<String,String>> getCollegeStatistics();

    Integer getReceiveCollegeCount();
    Integer getTodayCreateReceiveCount();
}