package com.zxy.hrbcu.meeting.dto;

public class NoticeDto {

    private String id;

    private String title;

    private String content;

    /**
     * 附件标识组
     */
    private String[] uploadFileIds;

    /**
     * 附件名组
     */
    private String[] uploadFileNames;

    /**
     * 附件路径组
     */
    private String[] uploadFileUrls;

    /**
     * serialVersionUID
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getUploadFileIds() {
        return uploadFileIds;
    }

    public void setUploadFileIds(String[] uploadFileIds) {
        this.uploadFileIds = uploadFileIds;
    }

    public String[] getUploadFileNames() {
        return uploadFileNames;
    }

    public void setUploadFileNames(String[] uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public String[] getUploadFileUrls() {
        return uploadFileUrls;
    }

    public void setUploadFileUrls(String[] uploadFileUrls) {
        this.uploadFileUrls = uploadFileUrls;
    }
}