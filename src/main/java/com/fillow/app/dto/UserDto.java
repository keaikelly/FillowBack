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

    //User 관련 응답
    @Getter
    @Builder
    public static class UserResponse{
        private Long userId;
        private String loginId;
        private String name;
        private String email;

        public static UserResponse from(User user) {
            //User 엔티티를 Dto로 변환하는 함수
            return UserResponse.builder()
                    .userId(user.getUserId())
                    .loginId(user.getLoginId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }
}
