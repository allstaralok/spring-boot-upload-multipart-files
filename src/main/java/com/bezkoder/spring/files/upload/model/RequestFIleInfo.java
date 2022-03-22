package com.bezkoder.spring.files.upload.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class RequestFIleInfo {
    @JsonProperty("file_uid")
    private String[] file_uid;

    public RequestFIleInfo() {
    }

    public RequestFIleInfo(String[] file_uid) {
        this.file_uid = file_uid;
    }

    public String[] getFile_uid() {
        return file_uid;
    }

    public void setFile_uid(String[] file_uid) {
        this.file_uid = file_uid;
    }

    @Override
    public String toString() {
        return "RequestFIleInfo{" +
                "file_uid=" + Arrays.toString(file_uid) +
                '}';
    }
}
