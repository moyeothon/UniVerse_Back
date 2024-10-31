package com.moyeothon.universe.repository;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.MemberRequestDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Member save(MemberRequestDto.SignUp memberSignUpDto);
  Optional<Member> findByUsername(String username);
  Optional<Member> findByRefreshToken(String refreshToken);
}
