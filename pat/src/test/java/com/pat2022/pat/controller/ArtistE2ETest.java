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

import com.pat2022.pat.model.ArtistModel;
import com.pat2022.pat.service.ArtistService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void artistGetTest(){
        Iterable<ArtistModel> artists = artistService.getArtist();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/artist";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<ArtistModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<ArtistModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(artists);
    }

    @Test
    public void artistByIdGetTest(){
        String artistId = "ID";
        ArtistModel artist = artistService.getArtistById(artistId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/artist/"+artistId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ArtistModel> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<ArtistModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(artist);
    }

    @Test
    public void artistPostTest(){
        ArtistModel artist = new ArtistModel();

        artist.setArtistId("artistId");
        artist.setArtistName("artistName");
        artist.setArtistGenres("artistGenres");
        artist.setArtistImage("artistImage");
        artist.setArtistFollowers(111);
        

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/artist";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ArtistModel> entity = new HttpEntity<>(artist,headers);

        ResponseEntity<ArtistModel> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<ArtistModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void artistDeleteTest(){

        String artistId = "eliminarId";
        ArtistModel artist = artistService.getArtistById(artistId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/artist/eliminarId";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ArtistModel> entity = new HttpEntity<>(artist,headers);

        ResponseEntity<ArtistModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<ArtistModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
