package com.fillow.repository;

import com.fillow.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    //로그인아이디로 유저 조회
    Optional<User> findByLoginId(String loginId);

    //중복체크
    boolean existsByLoginId(String loginId);
    boolean existsByEmail(String email);
}
