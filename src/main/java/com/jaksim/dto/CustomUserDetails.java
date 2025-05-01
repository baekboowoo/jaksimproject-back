package com.jaksim.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    User user;

    public CustomUserDetails(User user){
        this.user = user;

    }

    // 로그인한 User의 권한을 파악하는데 사용하는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> list = new ArrayList<>();

        GrantedAuthority a = new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return user.getUserRole();
            }
        };
        list.add(a);
        return list;
    }


    // 로그인한 user의 비밀번호로 판단해야 하는 부분이 어딘지 설정
    @Override
    public String getPassword() {
        return user.getUserPassword();
    }


    // 로그인한 user의 아이디로 판단해야 하는 부분이 어딘지 설정
    @Override
    public String getUsername() {
        return user.getUserId();
    }

    // 휴먼계정 활성화 할때 쓰는거 지금은 db에 없음
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 잠겼는지 안잠겼는지(ex 비밀번호 연속으로 틀릴때 ) 지금은 db에 없음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 인증이 만료 되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 영구차단 관련
    @Override
    public boolean isEnabled() {
        return true;
    }
}
