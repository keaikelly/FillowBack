package com.fillow.service;

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
    public User register(String loginId, String password, String name, String email ) {

        //중복체크
        if(userRepo.existsByLoginId(loginId)) {
            throw new IllegalArgumentException("이미 사용중인 id입니다.");
        }
        if(userRepo.existsByEmail(email)){
            throw new IllegalArgumentException("이미 사용중인 email입니다.");
        }

        //객체 생성
        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .build();
        return userRepo.save(user);
    }

    // loginId로 조회
    public User findByLoginId(String loginId){
        return userRepo.findByLoginId(loginId)
                .orElseThrow(()-> new IllegalArgumentException("해당 아이디의 유저 없음"));
    }

}
