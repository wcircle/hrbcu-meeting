package com.zxy.hrbcu.meeting.domain;

public class TMeetingCollege  {
    /**
     * t_meeting_college.id
     * 唯一标识
     */
    private String id;

    /**
     * t_meeting_college.name
     * 单位名称
     */
    private String name;

    /**
     * t_meeting_college.address
     * 单位地址
     */
    private String address;

    /**
     * t_meeting_college.postcode
     * 单位邮编
     */
    private String postcode;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_meeting_college.id
     *
     * @return the value of t_meeting_college.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_meeting_college.id
     *
     * @param id the value for t_meeting_college.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_meeting_college.name
     *
     * @return the value of t_meeting_college.name
     */
    public String getName() {
        return name;
    }

    /**
     * t_meeting_college.name
     *
     * @param name the value for t_meeting_college.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * t_meeting_college.address
     *
     * @return the value of t_meeting_college.address
     */
    public String getAddress() {
        return address;
    }

    /**
     * t_meeting_college.address
     *
     * @param address the value for t_meeting_college.address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * t_meeting_college.postcode
     *
     * @return the value of t_meeting_college.postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * t_meeting_college.postcode
     *
     * @param postcode the value for t_meeting_college.postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}