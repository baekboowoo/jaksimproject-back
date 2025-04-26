package com.jaksim.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 이 클래스는 설정을 위한 자바 클래스입니다.
@EnableWebSecurity // 설정은 설정인데, Security 설정 클래스 입니다.
public class SecurityConfig {

    // @Configuration 안의 @Bean은 --> 이미 만들어져있는 클래스 타입 객체를 등록하고 싶을때 사용
    // --> 우리가 실행할 일이 없는 메소드 --> spring boot가 최초로 1회 실행하고,
    // 실행 결과를 Container에 Bean으로 등록시키는 용도의 메소드
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Security 설정,
    // rerutn은 무조건 SecurityFilterChain 타입이어야한다.
    // 매개변수는 HTTPSecurity 를 인자로 받아와야한다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.cors((auth)-> auth.disable());

        //csrf 설정
        http.csrf((auth)-> auth.disable() );


        // csrf 설정
                //  크로스 사이트 요청 위조 공격을 방어하기 위한 설정
                //  이미 로그인이 완료된 사용자 --> 개인정보수정요청
        http.csrf((auth)->auth.disable());
        // 기본으로 뜨는 로그인 화면과 관련된 설정
        http.formLogin((auth)->auth.disable());

        // /api/join으로 GET요청은 로그인 하지 않아도 할 수 있도록 하고 싶음
        // authorizeHttpRequests --> 요청에 대한 권한을 설정하는 메소드
        // http.authorizeHttpRequests((aaa)->{aaa.설정()});
        http.authorizeHttpRequests((auth)->auth.requestMatchers("/api/join").permitAll()
                .anyRequest().authenticated()
        );

        // session --> jwt 방식은 세션 로그인 방식이 아니기 때문에 , session을 STATELESS 상태로 변경
        // STATEFUL --> 이전 트랜잭션에 대한 정보를 저장하는 것
        // STATELESS --> 과거 트랜잭션에 대한 정보를 저장하지 않음, 각 요청은 독립적으로 수행이 됨.
        http.sessionManagement((auth)->auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

}




