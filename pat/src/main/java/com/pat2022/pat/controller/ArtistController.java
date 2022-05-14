package com.pat2022.pat.controller;

import com.pat2022.pat.model.ArtistModel;
import com.pat2022.pat.service.ArtistService;

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
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artist")
    public ResponseEntity<Iterable<ArtistModel>> retriveArtist(){
        Iterable<ArtistModel> response = artistService.getArtist();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/artist/{artistId}")
    public ArtistModel getArtist(@PathVariable String artistId){
        return artistService.getArtistById(artistId);
    }

    @PostMapping("/artist")
    public ResponseEntity<String> createArtistById(@RequestBody ArtistModel artist, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            artistService.createArtist(artist);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/artist/{artistId}")
    public ResponseEntity<ArtistModel> deleteArtist(@PathVariable String artistId){
        artistService.deleteArtist(artistId);
        return ResponseEntity.noContent().build();
    }
    
}
