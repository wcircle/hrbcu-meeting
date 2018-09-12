package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TUserSleeping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TUserSleepingDao extends BaseDao<TUserSleeping> {
    List<Map<String,String>> getSleepList(Map param);

    int deleteByIds(HashMap map);
}