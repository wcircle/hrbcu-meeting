package com.zxy.hrbcu.meeting.domain;

import java.util.Date;

public class TMeetingNotice  {
    /**
     * t_meeting_notice.id
     * 唯一标识
     */
    private String id;

    /**
     * t_meeting_notice.main_id
     * 主表标识
     */
    private String mainId;

    /**
     * t_meeting_notice.title
     * 通知标题
     */
    private String title;

    /**
     * t_meeting_notice.content
     * 通知内容
     */
    private String content;

    /**
     * t_meeting_notice.remark
     * 备注
     */
    private String remark;

    /**
     * t_meeting_notice.create_staff_id
     * 创建人
     */
    private String createStaffId;

    /**
     * t_meeting_notice.create_time
     * 创建时间
     */
    private Date createTime;

    /**
     * t_meeting_notice.update_staff_id
     * 更新人
     */
    private String updateStaffId;

    /**
     * t_meeting_notice.update_time
     * 更新时间
     */
    private Date updateTime;

    /**
     * t_user_receive.state
     * 状态(-1-删除 1-有效)
     */
    private Integer state;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_meeting_notice.id
     *
     * @return the value of t_meeting_notice.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_meeting_notice.id
     *
     * @param id the value for t_meeting_notice.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_meeting_notice.main_id
     *
     * @return the value of t_meeting_notice.main_id
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * t_meeting_notice.main_id
     *
     * @param mainId the value for t_meeting_notice.main_id
     */
    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    /**
     * t_meeting_notice.title
     *
     * @return the value of t_meeting_notice.title
     */
    public String getTitle() {
        return title;
    }

    /**
     * t_meeting_notice.title
     *
     * @param title the value for t_meeting_notice.title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * t_meeting_notice.content
     *
     * @return the value of t_meeting_notice.content
     */
    public String getContent() {
        return content;
    }

    /**
     * t_meeting_notice.content
     *
     * @param content the value for t_meeting_notice.content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * t_meeting_notice.remark
     *
     * @return the value of t_meeting_notice.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_meeting_notice.remark
     *
     * @param remark the value for t_meeting_notice.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * t_meeting_notice.create_staff_id
     *
     * @return the value of t_meeting_notice.create_staff_id
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * t_meeting_notice.create_staff_id
     *
     * @param createStaffId the value for t_meeting_notice.create_staff_id
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId;
    }

    /**
     * t_meeting_notice.create_time
     *
     * @return the value of t_meeting_notice.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * t_meeting_notice.create_time
     *
     * @param createTime the value for t_meeting_notice.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * t_meeting_notice.update_staff_id
     *
     * @return the value of t_meeting_notice.update_staff_id
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * t_meeting_notice.update_staff_id
     *
     * @param updateStaffId the value for t_meeting_notice.update_staff_id
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    /**
     * t_meeting_notice.update_time
     *
     * @return the value of t_meeting_notice.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * t_meeting_notice.update_time
     *
     * @param updateTime the value for t_meeting_notice.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}