package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.ArtistModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @org.junit.jupiter.api.Test
    void getArtistById() {
        ArtistModel artistModel = artistService.getArtistById("ID");
        assertEquals("ID", artistModel.getArtistId());
    }

    @org.junit.jupiter.api.Test
    void deleteArtist() {
        artistService.deleteArtist("eliminarId");
        ArtistModel artistModel = null;
        try{
            artistModel = artistService.getArtistById("eliminarId");
        }catch(NoSuchElementException e){
            assertEquals(null, artistModel); //El album ya no existe en la DDBB
        }
    }

}