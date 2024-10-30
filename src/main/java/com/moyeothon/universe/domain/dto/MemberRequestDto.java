package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.emun.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

public class MemberRequestDto {

  @Data
  @Builder
  public static class SignUp {

    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
        message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
        message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요")
    @Size(min = 2, message = "사용자 이름이 너무 짧습니다.")
    @Pattern(regexp = "^[A-Za-z가-힣]+$", message = "사용자 이름은 한글 또는 알파벳만 입력해주세요.")
    private String nickname;

    public Member toEntity() {
      return Member.builder()
          .username(username)
          .password(password)
          .email(email)
          .nickname(nickname)
          .role(Role.USER)
          .build();
    }
  }
}
