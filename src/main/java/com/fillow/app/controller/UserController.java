package com.fillow.app.controller;

import com.fillow.app.dto.UserDto;
import com.fillow.domain.entity.User;
import com.fillow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.fillow.config.JwtTokenProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @PostMapping("/register")
    public UserDto.UserResponse register(@RequestBody UserDto.UserRegisterRequest request){
        User savedUser=userService.register(request);
        return UserDto.UserResponse.from(savedUser);
    }

    // 로그인
    @PostMapping("/login")
    public UserDto.UserLoginResponse login(@RequestBody UserDto.UserLoginRequest request) {
        User user = userService.login(request);
        String accessToken =jwtTokenProvider.createToken(
                user.getUserId(),
                user.getLoginId()
        );
        return UserDto.UserLoginResponse.from(user, accessToken);
    }

}
