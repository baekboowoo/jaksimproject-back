package com.jaksim.mapper;

import com.jaksim.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 이메일 중복 여부 확인 메소드
    public Integer isDuplicateEmail(String email);


    // 회원 정보 저장 메소드
    public void createUser(User user);

    // id를 받아와서 user 정보를 가져오는 메소드
    public User getUserById(String id);
}
