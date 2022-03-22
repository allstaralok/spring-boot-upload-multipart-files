package com.bezkoder.spring.files.upload.model;

public class UploadId {
    private String fileUid;
    private long timeStamp;
    private boolean uploaded;

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public UploadId(String fileUid, long timeStamp) {
        this.fileUid = fileUid;
        this.timeStamp = timeStamp;
    }

    public UploadId() {
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
