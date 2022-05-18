package com.pat2022.pat.service.dto;

public record AlbumDTO (
    String albumName,
    String albumArtist,
    String albumDate,
    String albumImage,
    int albumTracks
){}
