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

import com.pat2022.pat.model.AlbumModel;
import com.pat2022.pat.service.AlbumService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void albumGetTest(){
        Iterable<AlbumModel> albums = albumService.getAlbum();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/album";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<AlbumModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<AlbumModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(albums);
    }

    @Test
    public void albumByIdGetTest(){
        String albumId = "ID";
        AlbumModel album = albumService.getAlbumById(albumId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/album/"+albumId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<AlbumModel> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<AlbumModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(album);
    }

    @Test
    public void albumPostTest(){
        AlbumModel album = new AlbumModel();

        album.setAlbumId("albumId");
        album.setAlbumName("albumName");
        album.setAlbumImage("albumImage");
        album.setAlbumArtist("albumArtist");
        album.setAlbumDate("albumDate");
        album.setAlbumTracks(111);
        

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/album";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AlbumModel> entity = new HttpEntity<>(album,headers);

        ResponseEntity<AlbumModel> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<AlbumModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void songDeleteTest(){

        String albumId = "eliminarId";
        AlbumModel album = albumService.getAlbumById(albumId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/album/eliminarId";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AlbumModel> entity = new HttpEntity<>(album,headers);

        ResponseEntity<AlbumModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<AlbumModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
