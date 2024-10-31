package com.moyeothon.universe.service;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import com.moyeothon.universe.domain.emun.MemberStatus;
import com.moyeothon.universe.repository.MemberRepository;
import com.moyeothon.universe.util.security.SecurityUtil;
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

  public void signOut() {
    Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    member.setStatus(MemberStatus.DELETED);
  }
}
