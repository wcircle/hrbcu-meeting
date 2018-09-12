package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TUserEating;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TUserEatingDao extends BaseDao<TUserEating> {

    List<Map<String,String>> getEatList(Map param);

    int deleteByIds(HashMap map);
}