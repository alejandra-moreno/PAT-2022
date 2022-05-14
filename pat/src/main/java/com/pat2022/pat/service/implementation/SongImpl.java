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

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
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


    @Override
    public List<FavouritesJoinDTO> getFavourite(){
        String query =
        """
        SELECT USER.USER_NAME, SONG.SONG_NAME, SONG.SONG_ARTIST, SONG.SONG_ALBUM, SONG.SONG_DURATION
        FROM FAVOURITE
        LEFT JOIN USER ON (FAVOURITE.USER_ID = USER.USER_ID)  
        INNER JOIN SONG ON (FAVOURITE.SONG_ID = SONG.SONG_ID)      
        """;

        List<FavouritesJoinDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new FavouritesJoinDTO(
                rs.getString("USER_NAME"),
                rs.getString("SONG_NAME"),
                rs.getString("SONG_ARTIST"),
                rs.getString("SONG_ALBUM"),
                rs.getInt("SONG_DURATION")
            )
        );
        return joinList;
    }
    
}
