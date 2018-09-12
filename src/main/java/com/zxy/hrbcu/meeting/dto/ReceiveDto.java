package com.zxy.hrbcu.meeting.dto;

public class ReceiveDto {
    /**
     * t_user_receive.id
     * 唯一标识
     */
    private String id;

    private String userId;

    /**
     * t_user_receive.wx_no
     * 微信号
     */
    private String wxNo;

    /**
     * t_user_receive.bed_require
     * 住宿要求(1-标准间（2人合住）2-单人间（1人独住）3-不需要安排住宿)
     */
    private String bedRequire;

    /**
     * t_user_receive.represent_college
     * 代表单位
     */
    private String representCollege;

    /**
     * t_user_receive.work
     * 职务
     */
    private String work;

    /**
     * t_user_receive.is_private
     * 隐私设置(0-未选择，1-选中)
     */
    private String isPrivate;

    /**
     * t_user_receive.is_send
     * 是否接送站(0-未选择，1-选中)
     */
    private String isSend;

    /**
     * t_user_receive.arrive_type
     * 出发方式(1-飞机 2-火车)
     */
    private String arriveType;

    /**
     * t_user_receive.arrive_flight_no
     * 出发航班号/出发车次
     */
    private String arriveFlightNo;

    /**
     * t_user_receive.arrive_time
     * 预计到达时间
     */
    private String arriveTime;

    /**
     * t_user_receive.arrive_airport
     * 到达机场/到达车站(1-哈尔滨太平国际机场 2-哈尔滨站 3-哈尔滨西站 4-哈尔滨东站 5-哈尔滨北站)
     */
    private String arriveAirport;

    /**
     * t_user_receive.return_type
     * 回程方式(1-飞机 2-火车)
     */
    private String returnType;

    /**
     * t_user_receive.return_flight_no
     * 回程航班号/回程车次
     */
    private String returnFlightNo;

    /**
     * t_user_receive.return_time
     * 回程时间
     */
    private String returnTime;

    /**
     * t_user_receive.return_airport
     * 回程起飞机场/回程车站(1-哈尔滨太平国际机场 2-哈尔滨站 3-哈尔滨西站 4-哈尔滨东站 5-哈尔滨北站)
     */
    private String returnAirport;

    /**
     * t_user_receive.remark
     * 备注
     */
    private String remark;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_user_receive.id
     *
     * @return the value of t_user_receive.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_user_receive.id
     *
     * @param id the value for t_user_receive.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_user_receive.wx_no
     *
     * @return the value of t_user_receive.wx_no
     */
    public String getWxNo() {
        return wxNo;
    }

    /**
     * t_user_receive.wx_no
     *
     * @param wxNo the value for t_user_receive.wx_no
     */
    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * t_user_receive.bed_require
     *
     * @return the value of t_user_receive.bed_require
     */
    public String getBedRequire() {
        return bedRequire;
    }

    /**
     * t_user_receive.bed_require
     *
     * @param bedRequire the value for t_user_receive.bed_require
     */
    public void setBedRequire(String bedRequire) {
        this.bedRequire = bedRequire;
    }

    /**
     * t_user_receive.represent_college
     *
     * @return the value of t_user_receive.represent_college
     */
    public String getRepresentCollege() {
        return representCollege;
    }

    /**
     * t_user_receive.represent_college
     *
     * @param representCollege the value for t_user_receive.represent_college
     */
    public void setRepresentCollege(String representCollege) {
        this.representCollege = representCollege;
    }

    /**
     * t_user_receive.work
     *
     * @return the value of t_user_receive.work
     */
    public String getWork() {
        return work;
    }

    /**
     * t_user_receive.work
     *
     * @param work the value for t_user_receive.work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     * t_user_receive.is_private
     *
     * @return the value of t_user_receive.is_private
     */
    public String getIsPrivate() {
        return isPrivate;
    }

    /**
     * t_user_receive.is_private
     *
     * @param isPrivate the value for t_user_receive.is_private
     */
    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * t_user_receive.is_send
     *
     * @return the value of t_user_receive.is_send
     */
    public String getIsSend() {
        return isSend;
    }

    /**
     * t_user_receive.is_send
     *
     * @param isSend the value for t_user_receive.is_send
     */
    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    /**
     * t_user_receive.arrive_type
     *
     * @return the value of t_user_receive.arrive_type
     */
    public String getArriveType() {
        return arriveType;
    }

    /**
     * t_user_receive.arrive_type
     *
     * @param arriveType the value for t_user_receive.arrive_type
     */
    public void setArriveType(String arriveType) {
        this.arriveType = arriveType;
    }

    /**
     * t_user_receive.arrive_flight_no
     *
     * @return the value of t_user_receive.arrive_flight_no
     */
    public String getArriveFlightNo() {
        return arriveFlightNo;
    }

    /**
     * t_user_receive.arrive_flight_no
     *
     * @param arriveFlightNo the value for t_user_receive.arrive_flight_no
     */
    public void setArriveFlightNo(String arriveFlightNo) {
        this.arriveFlightNo = arriveFlightNo;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * t_user_receive.arrive_airport
     *
     * @return the value of t_user_receive.arrive_airport
     */
    public String getArriveAirport() {
        return arriveAirport;
    }

    /**
     * t_user_receive.arrive_airport
     *
     * @param arriveAirport the value for t_user_receive.arrive_airport
     */
    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    /**
     * t_user_receive.return_type
     *
     * @return the value of t_user_receive.return_type
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * t_user_receive.return_type
     *
     * @param returnType the value for t_user_receive.return_type
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * t_user_receive.return_flight_no
     *
     * @return the value of t_user_receive.return_flight_no
     */
    public String getReturnFlightNo() {
        return returnFlightNo;
    }

    /**
     * t_user_receive.return_flight_no
     *
     * @param returnFlightNo the value for t_user_receive.return_flight_no
     */
    public void setReturnFlightNo(String returnFlightNo) {
        this.returnFlightNo = returnFlightNo;
    }

    /**
     * t_user_receive.return_airport
     *
     * @return the value of t_user_receive.return_airport
     */
    public String getReturnAirport() {
        return returnAirport;
    }

    /**
     * t_user_receive.return_airport
     *
     * @param returnAirport the value for t_user_receive.return_airport
     */
    public void setReturnAirport(String returnAirport) {
        this.returnAirport = returnAirport;
    }

    /**
     * t_user_receive.remark
     *
     * @return the value of t_user_receive.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_user_receive.remark
     *
     * @param remark the value for t_user_receive.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}