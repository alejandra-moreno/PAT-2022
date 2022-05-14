package com.pat2022.pat.service;

import java.util.List;

import com.pat2022.pat.model.UserModel;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.pat2022.pat.service.dto.FavouritesJoinDTO;

public interface UserService extends UserDetailsService{
    Iterable<UserModel> getUser();
    UserModel getUserById(String userId);
    void deleteUser(String userId);
    void createUserService(UserModel user); 
    UserModel updateUser(String userId, UserModel user);
    void updatePassword(String userPassword, String userId);
    List<FavouritesJoinDTO> getFavourite();
}
