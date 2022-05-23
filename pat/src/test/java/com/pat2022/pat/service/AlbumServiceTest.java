package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.AlbumModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @org.junit.jupiter.api.Test
    void getAlbumById() {
        AlbumModel albumModel = albumService.getAlbumById("ID");
        assertEquals("ID", albumModel.getAlbumId());
    }

    @org.junit.jupiter.api.Test
    void deleteAlbum() {
        albumService.deleteAlbum("eliminarId");
        AlbumModel albumModel = null;
        try{
            albumModel = albumService.getAlbumById("eliminarId");
        }catch(NoSuchElementException e){
            assertEquals(null, albumModel); //El album ya no existe en la DDBB
        }
    }
}