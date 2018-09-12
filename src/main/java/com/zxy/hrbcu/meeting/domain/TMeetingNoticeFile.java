package com.zxy.hrbcu.meeting.domain;

public class TMeetingNoticeFile  {
    /**
     * t_meeting_notice_file.id
     * 唯一标识
     */
    private String id;

    /**
     * t_meeting_notice_file.notice_id
     * 通知表标识
     */
    private String noticeId;

    /**
     * t_meeting_notice_file.file_name
     * 文件名称
     */
    private String fileName;

    /**
     * t_meeting_notice_file.file_path
     * 文件路径
     */
    private String filePath;

    /**
     * t_meeting_notice_file.remark
     * 备注
     */
    private String remark;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * t_meeting_notice_file.id
     *
     * @return the value of t_meeting_notice_file.id
     */
    public String getId() {
        return id;
    }

    /**
     * t_meeting_notice_file.id
     *
     * @param id the value for t_meeting_notice_file.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * t_meeting_notice_file.notice_id
     *
     * @return the value of t_meeting_notice_file.notice_id
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * t_meeting_notice_file.notice_id
     *
     * @param noticeId the value for t_meeting_notice_file.notice_id
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * t_meeting_notice_file.file_name
     *
     * @return the value of t_meeting_notice_file.file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * t_meeting_notice_file.file_name
     *
     * @param fileName the value for t_meeting_notice_file.file_name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * t_meeting_notice_file.file_path
     *
     * @return the value of t_meeting_notice_file.file_path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * t_meeting_notice_file.file_path
     *
     * @param filePath the value for t_meeting_notice_file.file_path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * t_meeting_notice_file.remark
     *
     * @return the value of t_meeting_notice_file.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * t_meeting_notice_file.remark
     *
     * @param remark the value for t_meeting_notice_file.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}