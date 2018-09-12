package com.zxy.hrbcu.meeting.dao;

import com.zxy.hrbcu.meeting.domain.TMeetingNoticeFile;

public interface TMeetingNoticeFileDao extends BaseDao<TMeetingNoticeFile> {
    int deleteByCondition(TMeetingNoticeFile param);
}