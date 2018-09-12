package com.zxy.hrbcu.meeting.dto;

public class SleepDto {

    private Integer sleepOrder;

    private String address;

    private String roomNo;

    private String sleepDate;

    private String id;

    private String receiveId;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSleepOrder() {
        return sleepOrder;
    }

    public void setSleepOrder(Integer sleepOrder) {
        this.sleepOrder = sleepOrder;
    }

    public String getSleepDate() {
        return sleepDate;
    }

    public void setSleepDate(String sleepDate) {
        this.sleepDate = sleepDate;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }
}