package com.pat2022.pat.service.dto;

public record FavouritesJoinDTO (
    String userName,
    String songName,
    String songArtist,
    String songAlbum,
    Integer songDuration
){}
