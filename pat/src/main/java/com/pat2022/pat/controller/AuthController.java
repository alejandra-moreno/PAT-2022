package com.pat2022.pat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<Authentication> tokenLogIn(Authentication authentication){
        return new ResponseEntity<>(authentication,HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<Authentication> tokenSingUp(Authentication authentication){
        return new ResponseEntity<>(authentication,HttpStatus.OK);
    }



    
}
