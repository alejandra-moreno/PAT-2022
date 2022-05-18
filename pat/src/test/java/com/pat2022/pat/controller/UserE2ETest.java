package com.pat2022.pat.controller;



import com.pat2022.pat.model.UserModel;
import com.pat2022.pat.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userGetTest(){
        Iterable<UserModel> users = userService.getUser();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/users";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<UserModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<UserModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(users);
    }
    @Test
    public void userPostTest(){

        UserModel user = new UserModel();
        user.setUserId("1234rty");
        user.setUserPassword("1234tyui");
        user.setUserName("numhj");
        user.setUserEmail("userEmail@gamil.com");
        user.setUserAge(20);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/users";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserModel> entity = new HttpEntity<>(user,headers);

        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<String> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<String>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        
    }
    @Test
    public void userPutTest(){

        UserModel user = userService.getUserById("usuarioActualizar");
        user.setUserEmail("nuevo@gamil.com");

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/users/usuarioActualizar";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserModel> entity = new HttpEntity<>(user,headers);

        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<UserModel> result = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            entity,
            new ParameterizedTypeReference<UserModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        
    }

    @Test
    public void userDeleteTest(){

        UserModel user = userService.getUserById("usuarioEliminar");

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/users/usuarioEliminar";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserModel> entity = new HttpEntity<>(user,headers);

        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<UserModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<UserModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        
    }

}
