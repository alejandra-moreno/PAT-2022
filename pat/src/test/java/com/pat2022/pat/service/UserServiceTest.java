package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @org.junit.jupiter.api.Test
    void getUserById() {
        UserModel userModel = userService.getUserById("blancadepedr");
        assertEquals("blancadepedr", userModel.getUserId());
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
        userService.deleteUser("blancadepedr");
        UserModel userModel = null;
        try{
            userModel = userService.getUserById("blancadepedr");
        }catch(NoSuchElementException e){
            assertEquals(null, userModel); //El usuario ya no existe en la DDBB
        }
    }

    @org.junit.jupiter.api.Test
    void updateUser() {
        UserModel userDelete = userService.getUserById("usuarioEliminar");
        UserModel userModel = userService.updateUser("blancadepedr",userDelete);
        assertEquals("prueba@gmail.com", userModel.getUserEmail());
    }

    @org.junit.jupiter.api.Test
    void updatePassword() {
        userService.updatePassword("cambioPassword", "blancadepedr");
        UserModel userModel = userService.getUserById("blancadepedr");
        assertEquals("cambioPassword", userModel.getUserPassword());
    }
}