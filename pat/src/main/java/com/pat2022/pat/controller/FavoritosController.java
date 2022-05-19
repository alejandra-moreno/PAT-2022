package com.pat2022.pat.controller;

import com.pat2022.pat.model.FavoritosModel;
import com.pat2022.pat.service.FavoritosService;
import com.pat2022.pat.service.dto.AlbumDTO;
import com.pat2022.pat.service.dto.ArtistDTO;
import com.pat2022.pat.service.dto.EpisodeDTO;
import com.pat2022.pat.service.dto.SongDTO;

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
public class FavoritosController {
    @Autowired
    private FavoritosService favoritosService;

    @GetMapping("/favourites")
    public ResponseEntity<Iterable<FavoritosModel>> retriveFav(){
        Iterable<FavoritosModel> response = favoritosService.getFav();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/favourites/{userId}/{favId}")
    public FavoritosModel getFav(@PathVariable String userId, @PathVariable String favId){
        return favoritosService.getFavById(userId, favId);
    }

    @PostMapping("/favourites")
    public ResponseEntity<String> createFav(@RequestBody FavoritosModel fav, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            favoritosService.createFav(fav);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/favourites/{userId}/{favId}")
    public ResponseEntity<FavoritosModel> deleteSong(@PathVariable String userId, @PathVariable String favId){
        favoritosService.deleteFav(userId, favId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/favourites/song")
    public ResponseEntity<Iterable<SongDTO>> retriveFavSongs(){
        Iterable<SongDTO> response = favoritosService.getFavouriteSong();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/song/{userId}")
    public ResponseEntity<Iterable<SongDTO>> retriveFavSongsByUserId(@PathVariable String userId){
        Iterable<SongDTO> response = favoritosService.getFavouriteSongByUser(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/album")
    public ResponseEntity<Iterable<AlbumDTO>> retriveFavAlbums(){
        Iterable<AlbumDTO> response = favoritosService.getFavouriteAlbum();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/album/{userId}")
    public ResponseEntity<Iterable<AlbumDTO>> retriveFavAlbumsByUserId(@PathVariable String userId){
        Iterable<AlbumDTO> response = favoritosService.getFavouriteAlbumByUser(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/podcast")
    public ResponseEntity<Iterable<EpisodeDTO>> retriveFavPodcast(){
        Iterable<EpisodeDTO> response = favoritosService.getFavouriteEpisode();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/podcast/{userId}")
    public ResponseEntity<Iterable<EpisodeDTO>> retriveFavPodcastsByUserId(@PathVariable String userId){
        Iterable<EpisodeDTO> response = favoritosService.getFavouriteEpisodeByUser(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/artist")
    public ResponseEntity<Iterable<ArtistDTO>> retriveFavArtist(){
        Iterable<ArtistDTO> response = favoritosService.getFollowing();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/favourites/artist/{userId}")
    public ResponseEntity<Iterable<ArtistDTO>> retriveFavArtistByUserId(@PathVariable String userId){
        Iterable<ArtistDTO> response = favoritosService.getFollowingByUser(userId);
        return ResponseEntity.ok().body(response);
    }


}
