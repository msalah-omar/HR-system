package com.example.hrSystem.controller;


import com.example.hrSystem.handler.UserHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    public UserHandler userHandler;

    @GetMapping("/save")
    public ResponseEntity<?> save(){
        return userHandler.save();
    }

    @GetMapping("/login")
    public ResponseEntity<?> save(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "password") String password){
        return userHandler.login(username, password);
    }

}
