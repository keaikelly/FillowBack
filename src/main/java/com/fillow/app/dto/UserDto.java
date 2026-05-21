package com.fillow.app.dto;

import com.fillow.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    //회원가입 요청
    @Getter
    @NoArgsConstructor
    public static class UserRegisterRequest {
        private String loginId;
        private String password;
        private String name;
        private String email;
    }

    //회원가입+조회 응답
    @Getter
    @Builder
    public static class UserResponse {
        private Long userId;
        private String loginId;
        private String name;
        private String email;

        public static UserResponse from(User user) {
            return UserResponse.builder()
                    .userId(user.getUserId())
                    .loginId(user.getLoginId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }

    }

    //로그인 요청
    @Getter
    @NoArgsConstructor
    public static class UserLoginRequest {
        private String loginId;
        private String password;
    }

    // 로그인 응답
    @Getter
    @Builder
    public static class UserLoginResponse{
        private Long userId;
        private String loginId;
        private String name;
        private String email;
        private String accessToken;

        public static UserLoginResponse from(User user, String accessToken) {
            //User 엔티티를 Dto로 변환하는 함수
            return UserLoginResponse.builder()
                    .userId(user.getUserId())
                    .loginId(user.getLoginId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .accessToken(accessToken)
                    .build();
        }
    }
}
