package com.pat2022.pat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("USER")
public class UserModel {
    @Id
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private int userAge;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return this.userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public UserModel userId(String userId) {
        setUserId(userId);
        return this;
    }

    public UserModel userName(String userName) {
        setUserName(userName);
        return this;
    }

    public UserModel userPassword(String userPassword) {
        setUserPassword(userPassword);
        return this;
    }

    public UserModel userEmail(String userEmail) {
        setUserEmail(userEmail);
        return this;
    }

    public UserModel userAge(int userAge) {
        setUserAge(userAge);
        return this;
    }
}
