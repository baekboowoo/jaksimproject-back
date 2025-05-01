package com.jaksim.service;

import com.jaksim.dto.CustomUserDetails;
import com.jaksim.dto.User;
import com.jaksim.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getUserById(username);
        if(user == null) {
            return null;
        }

        // User타입의 값 중 필요한 값을 UserDetails에 담아서 전달 해야함!
        return new CustomUserDetails(user);

    }
}
