package com.moyeothon.universe.controller;

import com.moyeothon.universe.apiPayload.ApiResponse;
import com.moyeothon.universe.apiPayload.code.status.SuccessStatus;
import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import com.moyeothon.universe.domain.dto.MemberResponseDto;
import com.moyeothon.universe.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public ApiResponse<?> signUp(@RequestBody @Validated MemberRequestDto.SignUp signUpDto) {
    log.info("signUpDto: {}", signUpDto);
    Member member = memberService.signUp(signUpDto);
    return ApiResponse.of(SuccessStatus.MEMBER_JOIN, MemberResponseDto.SignUpDto.builder()
        .id(member.getId())
        .username(member.getUsername())
        .email(member.getEmail())
        .nickname(member.getNickname())
        .build());
  }

  @DeleteMapping
  public ApiResponse<?> signOut() {
    memberService.signOut();
    return ApiResponse.of(SuccessStatus.MEMBER_DELETE, "SignOut Success");
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return new ResponseEntity<>("test", HttpStatus.OK);
  }
}
