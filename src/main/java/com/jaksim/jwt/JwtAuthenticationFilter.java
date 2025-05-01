package com.jaksim.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    // Authentication 을 시도할 때 어떻게 시도할 건지 설정하는 메소드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // UsernamePasswordAuthenticationToken은 단순히 아이디와 비밀번호를 한번에 담기 위한 객체이다.
        // AuthenticationManager한테 넘겨줄떄는 아이디랑 비밀번호를 여기에 담아서 줘야함.
       String id = obtainUsername(request);
       String pwd = obtainPassword(request);

       UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken(id,pwd, null);

       return authenticationManager.authenticate(userInfo);
    }

    // 로그인 성공하면 무엇을 할지 설정하는 메소드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        // jwt를 만들고 응답으로 전달한다.

        System.out.println("성공입니다");

    }

    // 로그인 실패하면 무엇을 할지 설정하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        // 401 오류로 응답 전달
        System.out.println("실패입니다");
    }
}
