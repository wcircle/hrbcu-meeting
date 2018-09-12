package com.zxy.hrbcu.meeting.domain;

import java.util.Date;

public class TUserEating  {
    /**
     * t_user_eating.id
     * 主表标识
     */
    private String id;

    /**
     * t_user_eating.receive_id
     * 回执标识
     */
    private String receiveId;

    private String userId;

    /**
     * t_user_eating.address
     * 地点
     */
    private String address;

    /**
     * t_user_eating.room_no
     * 房间号
     */
    private String roomNo;

    /**
     * t_user_eating.table_no
     * 桌号
     */
    private String tableNo;

    /**
     * t_user_eating.eat_order
     * 用餐排序
     */
    private Integer eatOrder;

    /**
     * t_user_eating.remark
     * 备注
     */
    private String remark;

    /**
     * t_user_eating.create_staff_id
     * 创建人
     */
    private String createStaffId;

    /**
     * t_user_eating.create_time
     * 创建时间
     */
    private Date createTime;

    /**
     * t_user_eating.update_staff_id
     * 更新人
     */
    private String updateStaffId;

    /**
     * t_user_eating.update_time
     * 更新时间
     */
    private Date updateTime;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_user_eating.id
     *
     * @return the value of t_user_eating.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_user_eating.id
     *
     * @param id the value for t_user_eating.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_user_eating.receive_id
     *
     * @return the value of t_user_eating.receive_id
     */
    public String getReceiveId() {
        return receiveId;
    }

    /**
     * t_user_eating.receive_id
     *
     * @param receiveId the value for t_user_eating.receive_id
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
     * t_user_eating.address
     *
     * @return the value of t_user_eating.address
     */
    public String getAddress() {
        return address;
    }

    /**
     * t_user_eating.address
     *
     * @param address the value for t_user_eating.address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * t_user_eating.room_no
     *
     * @return the value of t_user_eating.room_no
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * t_user_eating.room_no
     *
     * @param roomNo the value for t_user_eating.room_no
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * t_user_eating.table_no
     *
     * @return the value of t_user_eating.table_no
     */
    public String getTableNo() {
        return tableNo;
    }

    /**
     * t_user_eating.table_no
     *
     * @param tableNo the value for t_user_eating.table_no
     */
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    /**
     * t_user_eating.eat_order
     *
     * @return the value of t_user_eating.eat_order
     */
    public Integer getEatOrder() {
        return eatOrder;
    }

    /**
     * t_user_eating.eat_order
     *
     * @param eatOrder the value for t_user_eating.eat_order
     */
    public void setEatOrder(Integer eatOrder) {
        this.eatOrder = eatOrder;
    }

    /**
     * t_user_eating.remark
     *
     * @return the value of t_user_eating.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_user_eating.remark
     *
     * @param remark the value for t_user_eating.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * t_user_eating.create_staff_id
     *
     * @return the value of t_user_eating.create_staff_id
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * t_user_eating.create_staff_id
     *
     * @param createStaffId the value for t_user_eating.create_staff_id
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId;
    }

    /**
     * t_user_eating.create_time
     *
     * @return the value of t_user_eating.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * t_user_eating.create_time
     *
     * @param createTime the value for t_user_eating.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * t_user_eating.update_staff_id
     *
     * @return the value of t_user_eating.update_staff_id
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * t_user_eating.update_staff_id
     *
     * @param updateStaffId the value for t_user_eating.update_staff_id
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    /**
     * t_user_eating.update_time
     *
     * @return the value of t_user_eating.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * t_user_eating.update_time
     *
     * @param updateTime the value for t_user_eating.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}