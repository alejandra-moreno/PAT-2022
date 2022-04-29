package com.pat2022.pat.service;

import com.pat2022.pat.model.UserModel;

public interface UserService {
    Iterable<UserModel> getUser();
    void deleteUser(String userId);
    void createUserService(UserModel user); 
    UserModel getUserById(String userId);
}
