package com.zxy.hrbcu.meeting.domain;

import java.util.Date;

public class TUUser  {
    /**
     * t_u_user.id
     * 唯一标识
     */
    private String id;

    /**
     * t_u_user.user_name
     * 姓名
     */
    private String userName;

    /**
     * t_u_user.phone
     * 手机号
     */
    private String phone;

    /**
     * t_u_user.password
     * 密码
     */
    private String password;

    /**
     * t_u_user.id_no
     * 身份证号
     */
    private String idNo;

    /**
     * t_u_user.gender
     * 性别(1-男 2-女)
     */
    private String gender;

    /**
     * t_u_user.remark
     * 备注
     */
    private String remark;

    /**
     * t_u_user.user_type
     * 用户类别(1-参会人员 2-管理人员)
     */
    private Integer userType;

    /**
     * t_u_user.create_staff_id
     * 创建人
     */
    private String createStaffId;

    /**
     * t_u_user.create_time
     * 创建时间
     */
    private Date createTime;

    /**
     * t_u_user.update_staff_id
     * 更新人
     */
    private String updateStaffId;

    /**
     * t_u_user.update_time
     * 更新时间
     */
    private Date updateTime;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_u_user.id
     *
     * @return the value of t_u_user.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_u_user.id
     *
     * @param id the value for t_u_user.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_u_user.user_name
     *
     * @return the value of t_u_user.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * t_u_user.user_name
     *
     * @param userName the value for t_u_user.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * t_u_user.phone
     *
     * @return the value of t_u_user.phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * t_u_user.phone
     *
     * @param phone the value for t_u_user.phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * t_u_user.password
     *
     * @return the value of t_u_user.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * t_u_user.password
     *
     * @param password the value for t_u_user.password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * t_u_user.id_no
     *
     * @return the value of t_u_user.id_no
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * t_u_user.id_no
     *
     * @param idNo the value for t_u_user.id_no
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * t_u_user.gender
     *
     * @return the value of t_u_user.gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * t_u_user.gender
     *
     * @param gender the value for t_u_user.gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * t_u_user.remark
     *
     * @return the value of t_u_user.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_u_user.remark
     *
     * @param remark the value for t_u_user.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * t_u_user.user_type
     *
     * @return the value of t_u_user.user_type
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * t_u_user.user_type
     *
     * @param userType the value for t_u_user.user_type
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * t_u_user.create_staff_id
     *
     * @return the value of t_u_user.create_staff_id
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * t_u_user.create_staff_id
     *
     * @param createStaffId the value for t_u_user.create_staff_id
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId;
    }

    /**
     * t_u_user.create_time
     *
     * @return the value of t_u_user.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * t_u_user.create_time
     *
     * @param createTime the value for t_u_user.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * t_u_user.update_staff_id
     *
     * @return the value of t_u_user.update_staff_id
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * t_u_user.update_staff_id
     *
     * @param updateStaffId the value for t_u_user.update_staff_id
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    /**
     * t_u_user.update_time
     *
     * @return the value of t_u_user.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * t_u_user.update_time
     *
     * @param updateTime the value for t_u_user.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}