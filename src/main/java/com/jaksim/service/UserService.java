package com.jaksim.service;

import com.jaksim.dto.User;
import com.jaksim.dto.UserDTO;
import com.jaksim.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    BCryptPasswordEncoder encoder;
    UserMapper userMapper;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserMapper userMapper){
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    // email이 존재하는지 확이하는 메소드
    public boolean isDuplicateEmail(String email){
        Integer res = userMapper.isDuplicateEmail(email);
        if(res == 0){
            return false;
        }
        return true;
    }

    // 회원가입 기능 구현
    public void joinProcess(UserDTO userDTO){
        // ID가 이미 존재하는지 확인

        // Email이 이미 존재하는지 확인



        // dto에 있는 값들을 User Entity에 옮기기
        User user = new User(); //DB에 저장될 형태 만들기
        user.setUserId(userDTO.getUserId());
        user.setUserEmail(userDTO.getUserEmail());

        // 사용자가 화면에 입력한 raw 비밀번호를 암호화 한다.
        user.setUserPassword(encoder.encode(userDTO.getUserPassword()));
        user.setUserPasswordQuestion(userDTO.getUserPasswordQuestion());
        user.setUserPasswordAnswer(userDTO.getUserPasswordAnswer());
        user.setUserRole("ROLE_USER");
        System.out.println(encoder.encode(userDTO.getUserPassword()));

        // user를 DB에 저장 --> MAPPER
        userMapper.createUser(user);

    }
}
