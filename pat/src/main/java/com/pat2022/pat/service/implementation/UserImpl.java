package com.pat2022.pat.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.pat2022.pat.model.UserModel;
import com.pat2022.pat.repository.UserRepository;
import com.pat2022.pat.service.UserService;
import com.pat2022.pat.service.dto.FavouritesJoinDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
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

    @Override
    public void updatePassword(String userPassword, String userId){
        if(userRepository.existsById(userId)){
            userRepository.updatePassword(userPassword, userId);
        }
    }

    @Override
    public UserModel updateUser(String userId, UserModel user){
        if(userRepository.existsById(userId)){
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId){
        UserModel user = userRepository.findById(userId).get();
        List<GrantedAuthority> auth = new ArrayList<>();
        UserDetails newUser = new User(user.getUserId(), user.getUserPassword(),auth);
        return newUser;
    }

    @Override
    public List<FavouritesJoinDTO> getFavourite(){
        String query =
        """
        SELECT USER.USER_NAME, SONG.SONG_NAME, SONG.SONG_ARTIST, SONG.SONG_ALBUM, SONG.SONG_DURATION
        FROM FAVOURITE
        LEFT JOIN USER ON (FAVOURITE.USER_ID = USER.USER_ID)  
        INNER JOIN SONG ON (FAVOURITE.SONG_ID = SONG.SONG_ID)      
        """;

        List<FavouritesJoinDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new FavouritesJoinDTO(
                rs.getString("USER_NAME"),
                rs.getString("SONG_NAME"),
                rs.getString("SONG_ARTIST"),
                rs.getString("SONG_ALBUM"),
                rs.getInt("SONG_DURATION")
            )
        );
        return joinList;
    }

}
