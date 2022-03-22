package com.bezkoder.spring.files.upload.model;

public class UploadIds {
    private String fileUid;
    private long timeStamp;
    private boolean uploaded;

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public UploadIds(String fileUid, long timeStamp) {
        this.fileUid = fileUid;
        this.timeStamp = timeStamp;
    }

    public UploadIds() {
    }

    public String getFileUid() {
        return fileUid;
    }

    public void setFileUid(String fileUid) {
        this.fileUid = fileUid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
