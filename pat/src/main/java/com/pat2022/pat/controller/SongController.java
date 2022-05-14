package com.pat2022.pat.controller;

import java.util.List;

import com.pat2022.pat.model.SongModel;
import com.pat2022.pat.service.SongService;
import com.pat2022.pat.service.dto.FavouritesJoinDTO;

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
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/song")
    public ResponseEntity<Iterable<SongModel>> retriveSong(){
        Iterable<SongModel> response = songService.getSong();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/song/{songId}")
    public SongModel getSong(@PathVariable String songId){
        return songService.getSongById(songId);
    }

    @GetMapping("/song/favourite")
    public ResponseEntity<Iterable<FavouritesJoinDTO>> retriveFavourite(){
        Iterable<FavouritesJoinDTO> response = songService.getFavourite();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/song")
    public ResponseEntity<String> createSongById(@RequestBody SongModel song, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            songService.CreateSong(song);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/song/{songId}")
    public ResponseEntity<SongModel> deleteSong(@PathVariable String songId){
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}
