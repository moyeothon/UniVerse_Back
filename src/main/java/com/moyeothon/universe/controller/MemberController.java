package com.moyeothon.universe.controller;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import com.moyeothon.universe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<?> signUp(@RequestBody @Validated MemberRequestDto.SignUp signUpDto) {
    memberService.signUp(signUpDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
