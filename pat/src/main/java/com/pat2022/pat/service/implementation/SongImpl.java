package com.pat2022.pat.service.implementation;

import java.util.List;

import com.pat2022.pat.model.SongModel;
import com.pat2022.pat.repository.SongRepository;
import com.pat2022.pat.service.SongService;
import com.pat2022.pat.service.dto.FavouritesJoinDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SongImpl implements SongService{

    @Autowired
    private SongRepository songRepository;

    
    public Iterable<SongModel> getSong() {
        
        return songRepository.findAll();
    }

    @Override
    public SongModel getSongById(String songId) {
        
        return songRepository.findById(songId).get();
    }

    @Override
    public void deleteSong(String songId) {
        songRepository.deleteById(songId);
        
    }

    @Override
    public void CreateSong(SongModel song) {
        String songId = song.getSongId();
        String songName = song.getSongName();
        String songArtist = song.getSongArtist();
        String songAlbum = song.getSongAlbum();
        int songDuration = song.getSongDuration();
        songRepository.createSong(songId,songName,songArtist,songAlbum,songDuration);
    }



    
}
