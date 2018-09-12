package com.zxy.hrbcu.meeting.domain;

public class TMeetingMain  {
    /**
     * t_meeting_main.id
     * 唯一标识
     */
    private String id;

    /**
     * t_meeting_main.meeting_name
     * 会议名称
     */
    private String meetingName;

    /**
     * t_meeting_main.remark
     * 备注
     */
    private String remark;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_meeting_main.id
     *
     * @return the value of t_meeting_main.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_meeting_main.id
     *
     * @param id the value for t_meeting_main.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_meeting_main.meeting_name
     *
     * @return the value of t_meeting_main.meeting_name
     */
    public String getMeetingName() {
        return meetingName;
    }

    /**
     * t_meeting_main.meeting_name
     *
     * @param meetingName the value for t_meeting_main.meeting_name
     */
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    /**
     * t_meeting_main.remark
     *
     * @return the value of t_meeting_main.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_meeting_main.remark
     *
     * @param remark the value for t_meeting_main.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}