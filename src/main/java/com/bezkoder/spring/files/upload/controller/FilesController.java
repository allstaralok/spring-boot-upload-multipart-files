package com.bezkoder.spring.files.upload.controller;

import com.bezkoder.spring.files.upload.model.FileInfo;
import com.bezkoder.spring.files.upload.model.RequestFIleInfo;
import com.bezkoder.spring.files.upload.model.UploadId;
import com.bezkoder.spring.files.upload.service.FilesStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload")
  public ResponseEntity< UploadId []> uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam(value = "uploadContentInfo", required = false) Optional<String> info) {
    String message = "";
    UploadId uploadId[] = new UploadId[files.length];
    //System.out.println(info);


    ObjectMapper objectMapper = new ObjectMapper();
    RequestFIleInfo requestFIleInfo = null;
    try {
      if(info.isPresent())
      requestFIleInfo = objectMapper.readValue( info.get(),RequestFIleInfo.class);
      System.out.println(requestFIleInfo);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    try {

      for (int i = 0; i < files.length; i++) {
        long unixTime = System.currentTimeMillis() / 1000L;
        uploadId[i] = new UploadId();
        if(requestFIleInfo != null && requestFIleInfo.getFile_uid()[i] !=null) {
          System.out.println(requestFIleInfo.getFile_uid()[i]);
          uploadId[i].setFileUid(""+requestFIleInfo.getFile_uid()[i]);
        }

        else{uploadId[i].setFileUid("");}
        uploadId[i].setTimeStamp(unixTime);
        uploadId[i].setUploaded(true);
      }

      return ResponseEntity.status(HttpStatus.OK).body(uploadId);
    } catch (Exception e) {
      message = "Fail to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }
  }


  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
