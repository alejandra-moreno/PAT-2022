package com.pat2022.pat.controller;

import com.pat2022.pat.model.AlbumModel;
import com.pat2022.pat.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;

    @GetMapping("/album")
    public ResponseEntity<Iterable<AlbumModel>>retriveALbum(){

        Iterable<AlbumModel> response = albumService.getAlbum();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/album/{albumId}")
    public AlbumModel getAlbum(@PathVariable String albumId){

        return albumService.getAlbumById(albumId);
    }

    @PostMapping("/album")
    public ResponseEntity<String> createAlbumById(@RequestBody AlbumModel album,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            albumService.createAlbum(album);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/album/{albumId}")
    public ResponseEntity<AlbumModel> deleteAlbum(@PathVariable String albumId){

        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }

}
