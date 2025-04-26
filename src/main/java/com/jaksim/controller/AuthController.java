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

    @GetMapping("/api/join")
    public boolean duplicateEmail(@RequestParam(name = "email")String email){
        return userService.isDuplicateEmail(email);
    }

    @PostMapping ("/api/join")
    public String joinProcess(@RequestBody UserDTO userDTO){
        userService.joinProcess(userDTO);
        return "안녕하세요";
    }

    @GetMapping("/api/test")
    public String test2(){
        return "test2";
    }
}

