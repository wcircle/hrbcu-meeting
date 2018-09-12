package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TUUser;

import java.util.List;
import java.util.Map;

public interface TUUserDao extends BaseDao<TUUser> {
    List<TUUser> checkLoginUserName(Map param);
    Integer getTodayRegisterUserCount();
    List<Map<String,String>> getUserListForExcel();
}