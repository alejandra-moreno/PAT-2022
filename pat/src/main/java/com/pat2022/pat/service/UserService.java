package com.pat2022.pat.service;

import com.pat2022.pat.model.UserModel;

public interface UserService {
    Iterable<UserModel> getUser();
    UserModel getUserById(String userId);
    void deleteUser(String userId);
    void createUserService(UserModel user); 
    UserModel updateUser(String userId, UserModel user);
    void updatePassword(String userPassword, String userId);
    
}
