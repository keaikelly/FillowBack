package com.fillow.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {     //환경변수 받아서 JWT 생성+검증

    private final SecretKey secretKey; //토큰에 서명용 비밀키 (환경변수)
    private final long expiration; //토큰 유효기간 (로그인 유지기간)

    public JwtTokenProvider(
            //@Value로 application.properties에서 값 가져옴
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        //secret 환경변수 문자열 비밀키로 암호화 키 객체 생성 및 jwt 서명
        this.expiration = expiration;
    }

    public String createToken(Long userId, String loginId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        //토큰에 들어있는 정보: 사용자식별, 발급시간, 만료시간, 서명,
        return Jwts.builder()
                .subject(loginId)
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getLoginId(String token) {
        return getClaims(token).getSubject(); //토큰 내 sub 꺼내 로그인아이디 반환
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class); //토큰 내 userId 꺼내 반환
    }

    public boolean validateToken(String token) { //토큰 검증
        try {
            getClaims(token);//토큰 검사
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) { //실제 검증 함수
        return Jwts.parser() //파서
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload(); //jwt 내 데이터 꺼냄
    }
}