package com.zxy.hrbcu.meeting.domain;

import java.util.Date;

public class TUserSleeping {
    /**
     * t_user_sleeping.id
     * 唯一标识
     */
    private String id;

    /**
     * t_user_sleeping.receive_id
     * 回执标识
     */
    private String receiveId;

    private String userId;

    /**
     * t_user_sleeping.address
     * 地点
     */
    private String address;

    /**
     * t_user_sleeping.room_no
     * 房间号
     */
    private String roomNo;

    /**
     * t_user_sleeping.sleep_date
     * 住宿时间
     */
    private Date sleepDate;

    /**
     * t_user_sleeping.sleep_order
     * 住宿排序
     */
    private Integer sleepOrder;

    /**
     * t_user_sleeping.create_staff_id
     * 创建人
     */
    private String createStaffId;

    /**
     * t_user_sleeping.create_time
     * 创建时间
     */
    private Date createTime;

    /**
     * t_user_sleeping.update_staff_id
     * 更新人
     */
    private String updateStaffId;

    /**
     * t_user_sleeping.update_time
     * 更新时间
     */
    private Date updateTime;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_user_sleeping.id
     *
     * @return the value of t_user_sleeping.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_user_sleeping.id
     *
     * @param id the value for t_user_sleeping.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_user_sleeping.receive_id
     *
     * @return the value of t_user_sleeping.receive_id
     */
    public String getReceiveId() {
        return receiveId;
    }

    /**
     * t_user_sleeping.receive_id
     *
     * @param receiveId the value for t_user_sleeping.receive_id
     */
    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * t_user_sleeping.address
     *
     * @return the value of t_user_sleeping.address
     */
    public String getAddress() {
        return address;
    }

    /**
     * t_user_sleeping.address
     *
     * @param address the value for t_user_sleeping.address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * t_user_sleeping.room_no
     *
     * @return the value of t_user_sleeping.room_no
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * t_user_sleeping.room_no
     *
     * @param roomNo the value for t_user_sleeping.room_no
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * t_user_sleeping.sleep_date
     *
     * @return the value of t_user_sleeping.sleep_date
     */
    public Date getSleepDate() {
        return sleepDate;
    }

    /**
     * t_user_sleeping.sleep_date
     *
     * @param sleepDate the value for t_user_sleeping.sleep_date
     */
    public void setSleepDate(Date sleepDate) {
        this.sleepDate = sleepDate;
    }

    /**
     * t_user_sleeping.sleep_order
     *
     * @return the value of t_user_sleeping.sleep_order
     */
    public Integer getSleepOrder() {
        return sleepOrder;
    }

    /**
     * t_user_sleeping.sleep_order
     *
     * @param sleepOrder the value for t_user_sleeping.sleep_order
     */
    public void setSleepOrder(Integer sleepOrder) {
        this.sleepOrder = sleepOrder;
    }

    /**
     * t_user_sleeping.create_staff_id
     *
     * @return the value of t_user_sleeping.create_staff_id
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * t_user_sleeping.create_staff_id
     *
     * @param createStaffId the value for t_user_sleeping.create_staff_id
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId;
    }

    /**
     * t_user_sleeping.create_time
     *
     * @return the value of t_user_sleeping.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * t_user_sleeping.create_time
     *
     * @param createTime the value for t_user_sleeping.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * t_user_sleeping.update_staff_id
     *
     * @return the value of t_user_sleeping.update_staff_id
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * t_user_sleeping.update_staff_id
     *
     * @param updateStaffId the value for t_user_sleeping.update_staff_id
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    /**
     * t_user_sleeping.update_time
     *
     * @return the value of t_user_sleeping.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * t_user_sleeping.update_time
     *
     * @param updateTime the value for t_user_sleeping.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}