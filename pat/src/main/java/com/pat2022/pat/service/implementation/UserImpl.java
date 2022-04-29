package com.pat2022.pat.service.implementation;

import com.pat2022.pat.model.UserModel;
import com.pat2022.pat.repository.UserRepository;
import com.pat2022.pat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService{

    @Autowired private UserRepository userRepository;

    @Override
    public void createUserService(UserModel user) {
        String userId = user.getUserId();
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String userEmail = user.getUserEmail();
        int userAge = user.getUserAge();
        userRepository.createUser(userId, userName, userPassword, userEmail, userAge);
    }

    @Override
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    @Override
    public Iterable<UserModel> getUser() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getUserById(String id) {
        return userRepository.findById(id).get();
    }

}
