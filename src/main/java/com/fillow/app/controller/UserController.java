package com.fillow.app.controller;

import com.fillow.app.dto.UserDto;
import com.fillow.domain.entity.User;
import com.fillow.repository.UserRepo;
import com.fillow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/register")
    public UserDto.UserResponse register(@RequestBody UserDto.UserRegisterRequest request){
        User savedUser=userService.register(request);
        return UserDto.UserResponse.from(savedUser);
    }

    // 로그인
    @PostMapping("/login")
    public UserDto.UserResponse login(@RequestBody UserDto.UserLoginRequest request) {
        User user = userService.login(request);
        return UserDto.UserResponse.from(user);
    }

}
