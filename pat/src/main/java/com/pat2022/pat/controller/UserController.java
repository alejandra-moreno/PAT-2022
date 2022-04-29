package com.pat2022.pat.controller;

import com.pat2022.pat.model.UserModel;
import com.pat2022.pat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserModel>> retriveUsers(){

       Iterable<UserModel> response = userService.getUser();
       return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/{userId}")
    public UserModel getUser(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUserById(
        @RequestBody UserModel user,
        BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
            }else{
                userService.createUserService(user);
                return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
            }
        }

    //Borrar usuario
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}