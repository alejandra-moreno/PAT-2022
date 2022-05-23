package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.EpisodeModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class EpisodeServiceTest {

    @Autowired
    private EpisodeService episodeService;

    @org.junit.jupiter.api.Test
    void getEpisodeById() {
        EpisodeModel episodeModel = episodeService.getEpisodeById("ID");
        assertEquals("ID", episodeModel.getEpisodeId());
    }

    @org.junit.jupiter.api.Test
    void deleteEpisode() {
        episodeService.deleteEpisode("eliminarId");
        EpisodeModel episodeModel = null;
        try{
            episodeModel = episodeService.getEpisodeById("eliminarId");
        }catch(NoSuchElementException e){
            assertEquals(null, episodeModel); //El album ya no existe en la DDBB
        }
    }

}