package com.pat2022.pat.repository;

import com.pat2022.pat.model.SongModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SongRepository extends CrudRepository<SongModel,String>{
    @Query("SELECT * FROM SONG WHERE SOND.SONG_ID = :songId")
    public Iterable <SongModel> getSongById(String songId);

    @Modifying
    @Query("INSET INTO SONG (SONG_ID,SONG_NAME,SONG_ARTIST,SONG_ALBUM,SONG_DURATION) VALUES (:songId, :songName, :songArtist, :songAlbum, :songDuration)")
    public void createSong(String songId, String songName, String songArtist, String songAlbum, int songDuration);
}
