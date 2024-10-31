package com.moyeothon.universe.service;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import com.moyeothon.universe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public Member signUp(MemberRequestDto.SignUp signUpDto) {
    signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    return memberRepository.save(signUpDto.toEntity());
  }

}
