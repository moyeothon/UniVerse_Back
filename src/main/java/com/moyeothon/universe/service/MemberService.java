package com.moyeothon.universe.service;

import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.MemberHandler;
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
    memberRepository.findByUsername(signUpDto.getUsername()).ifPresent(
        member -> {
          throw new MemberHandler(ErrorStatus.MEMBER_USERNAME_ALREADY_EXIST);
        }
    );
    memberRepository.findByEmail(signUpDto.getEmail()).ifPresent(
        member -> {
          throw new MemberHandler(ErrorStatus.MEMBER_EMAIL_ALREADY_EXIST);
        }
    );

    signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    return memberRepository.save(signUpDto.toEntity());
  }

  public void signOut() {
    Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
        .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    member.setStatus(MemberStatus.DELETED);
  }
}
