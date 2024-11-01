package com.moyeothon.universe.controller;

import com.moyeothon.universe.apiPayload.ApiResponse;
import com.moyeothon.universe.apiPayload.code.status.SuccessStatus;
import com.moyeothon.universe.domain.Record;
import com.moyeothon.universe.domain.dto.RecordRequestDto;
import com.moyeothon.universe.service.RecordService;
import com.moyeothon.universe.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

  private final RecordService recordService;
  private final S3Service s3Service;

  @PostMapping
  public ApiResponse<?> postRecord(@RequestBody RecordRequestDto.SaveRecord saveRecord) {
    Record record = recordService.postRecord(saveRecord);
    return ApiResponse.of(SuccessStatus.RECORD_ADD, record);
  }

  @GetMapping("/{id}")
  public ApiResponse<?> getRecordDetail(@PathVariable Long id, Pageable pageable) {
    Record record = recordService.getRecord(id);
    return ApiResponse.of(SuccessStatus.RECORD_GET, record);
  }

  @GetMapping("/my")
  public ApiResponse<?> myRecordList(Pageable pageable) {
    Page<Record> myRecordList = recordService.getMyRecordList(pageable);
    return ApiResponse.of(SuccessStatus.RECORD_GET_MY, myRecordList);
  }

  @PostMapping("/image")
  public ApiResponse<?> imageUpload(@RequestPart(value = "image", required = false) MultipartFile image){
    String profileImage = s3Service.saveFile(image);
    return ApiResponse.of(SuccessStatus.RECORD_IMAGE_ADD, profileImage);
  }
}
