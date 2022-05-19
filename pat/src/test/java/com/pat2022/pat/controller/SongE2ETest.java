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

import com.pat2022.pat.model.SongModel;
import com.pat2022.pat.service.SongService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private SongService songService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void songGetTest(){
        Iterable<SongModel> songs = songService.getSong();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/song";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<SongModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<SongModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(songs);
    }

    @Test
    public void songByIdGetTest(){
        String songId = "ID";
        SongModel song = songService.getSongById(songId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/song/"+songId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<SongModel> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<SongModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(song);
    }

    @Test
    public void songPostTest(){
        SongModel song = new SongModel();
        song.setSongId("pruebaId");
        song.setSongName("nameSong");
        song.setSongArtist("artistSong");
        song.setSongAlbum("albumSong");
        song.setSongDuration(3);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/song";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SongModel> entity = new HttpEntity<>(song,headers);

        ResponseEntity<SongModel> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<SongModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void songDeleteTest(){

        String songId = "eliminarId";
        SongModel song = songService.getSongById(songId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/song/eliminarId";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SongModel> entity = new HttpEntity<>(song,headers);

        ResponseEntity<SongModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<SongModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
