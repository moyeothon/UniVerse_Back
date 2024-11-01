package com.moyeothon.universe.controller;

import com.moyeothon.universe.apiPayload.ApiResponse;
import com.moyeothon.universe.apiPayload.code.status.SuccessStatus;
import com.moyeothon.universe.domain.Record;
import com.moyeothon.universe.domain.dto.RecordRequestDto;
import com.moyeothon.universe.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

  private final RecordService recordService;

  @PostMapping
  public ApiResponse<?> postRecord(@RequestBody RecordRequestDto.SaveRecord saveRecord) {
    Record record = recordService.postRecord(saveRecord);
    return ApiResponse.of(SuccessStatus.RECORD_ADD, record);
  }
}
