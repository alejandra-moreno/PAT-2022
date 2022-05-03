package com.pat2022.pat.repository;

import com.pat2022.pat.model.UserModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,String>{
    
    @Query("SELECT * FROM USER WHERE USER.USER_ID = :userId")
    public Iterable <UserModel> getUserById(String userId);

    @Modifying
    @Query("INSERT INTO USER (USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_AGE) VALUES (:userId,:userName,:userPassword,:userEmail,:userAge)")
    public void createUser(String userId,String userName,String userPassword,String userEmail,int userAge);

}
