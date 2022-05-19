package com.pat2022.pat.controller;
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
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import com.pat2022.pat.model.FavoritosModel;
import com.pat2022.pat.service.FavoritosService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FavoritosE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private FavoritosService favoritosService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void favGetTest(){
        Iterable<FavoritosModel> fav = favoritosService.getFav();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/favourites";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<FavoritosModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<FavoritosModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(fav);
    }


    @Test
    public void favPostTest(){
        FavoritosModel fav = new FavoritosModel();
        fav.setUserId("userId");
        fav.setFavId("favId");
        fav.setTipo("tipo");

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/favourites";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FavoritosModel> entity = new HttpEntity<>(fav,headers);

        ResponseEntity<FavoritosModel> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<FavoritosModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void favDeleteTest(){

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/favourites/usuarioEliminar/deleteIDSONG";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FavoritosModel> entity = new HttpEntity<>(headers);

        ResponseEntity<FavoritosModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<FavoritosModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
