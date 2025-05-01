package com.jaksim.controller;

import com.jaksim.dto.UserDTO;
import com.jaksim.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    // /login 으로 POST요청을 만들어 놓았다고 가정
    // Spring Security 프레임워크를 설치하면
    // 자동으로 /login POST 요청에 대한 요청과 응답이 설정이 된다.

    @PostMapping



    @GetMapping("/api/join")
    public boolean duplicateEmail(@RequestParam(name = "email")String email){

        return userService.isDuplicateEmail(email);

    }

    @PostMapping ("/api/join")
    public String joinProcess(@RequestBody UserDTO userDTO){
        userService.joinProcess(userDTO);
        return "성공";
    }

    @GetMapping("/api/test")
    public String test2(){
        return "test2";
    }
}

