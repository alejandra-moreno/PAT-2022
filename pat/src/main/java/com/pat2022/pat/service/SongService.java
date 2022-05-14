package com.pat2022.pat.service;

import java.util.List;

import com.pat2022.pat.model.SongModel;
import com.pat2022.pat.service.dto.FavouritesJoinDTO;

public interface SongService {
    
    Iterable<SongModel> getSong();
    SongModel getSongById(String songId);
    void deleteSong(String songId);
    void CreateSong(SongModel song);
    public List<FavouritesJoinDTO> getFavourite();

}
