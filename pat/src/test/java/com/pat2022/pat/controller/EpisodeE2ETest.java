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

import com.pat2022.pat.model.EpisodeModel;
import com.pat2022.pat.service.EpisodeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EpisodeE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void episodeGetTest(){
        Iterable<EpisodeModel> episodes = episodeService.getEpisode();

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/episode";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<EpisodeModel>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<EpisodeModel>>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(episodes);
    }

    @Test
    public void episodeByIdGetTest(){
        String episodeId = "ID";
        EpisodeModel episode = episodeService.getEpisodeById(episodeId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/episode/"+episodeId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<EpisodeModel> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<EpisodeModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(episode);
    }

    @Test
    public void episodePostTest(){
        EpisodeModel episode = new EpisodeModel();
        episode.setEpisodeId("episodeId");
        episode.setEpisodeDescription("episodeDescription");
        episode.setEpisodeImage("episodeImage");
        episode.setEpisodeName("episodeName");
        episode.setEpisodePublisher("episodePublisher");
        episode.setEpisodeTracks(111);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/episode";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<EpisodeModel> entity = new HttpEntity<>(episode,headers);

        ResponseEntity<EpisodeModel> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<EpisodeModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void episodeDeleteTest(){

        String episodeId = "eliminarId";
        EpisodeModel episode = episodeService.getEpisodeById(episodeId);

        String url = "http://localhost:"+Integer.toString(port)+"/api/v1/episode/eliminarId";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<EpisodeModel> entity = new HttpEntity<>(episode,headers);

        ResponseEntity<EpisodeModel> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<EpisodeModel>() {}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
