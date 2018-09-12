package com.zxy.hrbcu.meeting.dao;

import java.util.List;

public interface BaseDao<T> {
    T selectByPrimaryKey(String var1);

    int deleteByPrimaryKey(String var1);

    int insertSelective(T var1);

    int updateByPrimaryKeySelective(T var1);

    List<T> selectBySelective(T var1);

}