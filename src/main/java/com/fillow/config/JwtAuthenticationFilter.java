package com.fillow.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //API마다 JWT 확인 필터 기능

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 프론트에서 받은 Authorization 헤더 가져오기
        String bearerToken = request.getHeader("Authorization");

        // Bearer 토큰인지 확인
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            // "Bearer " 제거
            String token = bearerToken.substring(7);

            // 토큰 검증
            if (jwtTokenProvider.validateToken(token)) {

                String loginId = jwtTokenProvider.getLoginId(token); //jwt내 loginId 꺼냄


                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                loginId,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_USER"))//유저 권한 새성
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("JWT 인증 성공: " + loginId);
            }
        }

        filterChain.doFilter(request, response);
    }
}