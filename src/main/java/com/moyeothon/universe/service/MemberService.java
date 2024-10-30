package com.moyeothon.universe.service;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import com.moyeothon.universe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public void signUp(MemberRequestDto.SignUp signUpDto) {
    memberRepository.save(signUpDto.toEntity());
  }

}
