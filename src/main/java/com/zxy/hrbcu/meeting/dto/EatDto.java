package com.zxy.hrbcu.meeting.dto;

public class EatDto {

    private Integer eatOrder;

    private String address;

    private String roomNo;

    private String tableNo;

    private String id;

    /**
     * 用户回执数组
     */
    private String[] selectedReceiveId;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    public Integer getEatOrder() {
        return eatOrder;
    }

    public void setEatOrder(Integer eatOrder) {
        this.eatOrder = eatOrder;
    }

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

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getSelectedReceiveId() {
        return selectedReceiveId;
    }

    public void setSelectedReceiveId(String[] selectedReceiveId) {
        this.selectedReceiveId = selectedReceiveId;
    }
}