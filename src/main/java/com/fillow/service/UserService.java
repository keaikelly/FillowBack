package com.fillow.service;

import com.fillow.app.dto.UserDto;
import com.fillow.domain.entity.User;
import com.fillow.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //생성자를 자동으로 만들어줌
@Transactional(readOnly = true)

public class UserService {
    private final UserRepo userRepo; //UserRepo로 DB 작업. 변경X

    //회원가입
    @Transactional
    public User register(UserDto.UserRegisterRequest request) {

        //중복체크
        if(userRepo.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 사용중인 id입니다.");
        }
        if(userRepo.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 email입니다.");
        }

        //객체 생성
        User user = User.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .name(request.getName())
                .email(request.getEmail())
                .build();
        return userRepo.save(user);
    }

    // loginId로 로그인
    public User login(UserDto.UserLoginRequest request){

        User user = userRepo.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
