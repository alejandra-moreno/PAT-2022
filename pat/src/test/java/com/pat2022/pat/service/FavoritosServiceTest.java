package com.pat2022.pat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import com.pat2022.pat.model.FavoritosModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class FavoritosServiceTest {

    @Autowired
    private FavoritosService favoritosService;

    @org.junit.jupiter.api.Test
    void getFavById() {
        FavoritosModel favoritosModel = favoritosService.getFavById("usuario","ID");
        assertEquals("ID", favoritosModel.getFavId());
    }

    @org.junit.jupiter.api.Test
    void deleteFav() {
        favoritosService.deleteFav("usuarioEliminar","deleteIDSONG");
        FavoritosModel favoritosModel = null;
        try{
            favoritosModel = favoritosService.getFavById("usuarioEliminar","deleteIDSONG");
        }catch(NoSuchElementException e){
            assertEquals(null, favoritosModel); //El album ya no existe en la DDBB
        }
    }
    
}