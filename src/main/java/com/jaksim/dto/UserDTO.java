package com.jaksim.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userId;
    private String userEmail;
    private String userPassword;
    private Integer userPasswordQuestion;
    private String userPasswordAnswer;
    private String userRole;
}
