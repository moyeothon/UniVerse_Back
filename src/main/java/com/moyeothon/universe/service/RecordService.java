package com.moyeothon.universe.service;

import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.MemberHandler;
import com.moyeothon.universe.apiPayload.exception.handler.RecordHandler;
import com.moyeothon.universe.domain.Record;
import com.moyeothon.universe.domain.dto.RecordRequestDto;
import com.moyeothon.universe.repository.MemberRepository;
import com.moyeothon.universe.repository.RecordRepository;
import com.moyeothon.universe.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService {

  private final RecordRepository recordRepository;
  private final MemberRepository memberRepository;

  public Record postRecord(RecordRequestDto.SaveRecord saveRecord) {
    Long ownerId = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
        .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND))
        .getId();

    recordRepository.findByOwnerIdAndMovieId(ownerId, saveRecord.getMovieId())
        .ifPresent(record -> new RecordHandler(ErrorStatus.RECORD_ALREADY_EXISTS));

    saveRecord.setOwnerId(ownerId);
    return recordRepository.save(saveRecord);
  }
}
