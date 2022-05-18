package com.pat2022.pat.service;

import com.pat2022.pat.model.SongModel;


public interface SongService {
    
    Iterable<SongModel> getSong();
    SongModel getSongById(String songId);
    void deleteSong(String songId);
    void CreateSong(SongModel song);


}
