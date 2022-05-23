package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.SongModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class SongServiceTest {

    @Autowired
    private SongService songService;

    @org.junit.jupiter.api.Test
    void getSongById() {
        SongModel songModel = songService.getSongById("ID");
        assertEquals("ID", songModel.getSongId());
    }

    @org.junit.jupiter.api.Test
    void deleteSong() {
        songService.deleteSong("eliminarId");
        SongModel songModel = null;
        try{
            songModel = songService.getSongById("eliminarId");
        }catch(NoSuchElementException e){
            assertEquals(null, songModel); //El album ya no existe en la DDBB
        }
    }

}