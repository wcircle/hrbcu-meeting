package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TMeetingNotice;

import java.util.HashMap;
import java.util.List;

public interface TMeetingNoticeDao extends BaseDao<TMeetingNotice> {

    List<TMeetingNotice> getMeetingNoticeList();

    int updateState(HashMap map);
}