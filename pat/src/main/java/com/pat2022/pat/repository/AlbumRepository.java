package com.pat2022.pat.repository;
import com.pat2022.pat.model.AlbumModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<AlbumModel,String>{
    
    @Query("SELECT * FROM ALBUM WHERE ALBUM.ALBUM_ID = :albumId")
    public Iterable <AlbumModel> getAlbumById(String albumId);

    @Modifying
    @Query("INSERT INTO ALBUM (ALBUM_ID, ALBUM_NAME, ALBUM_ARTIST, ALBUM_DATE, ALBUM_IMAGE, ALBUM_TRACKS) VALUES (:albumId,:albumName,:albumArtist,:albumDate,:albumImage,:albumTracks)")
    public void createAlbum(String albumId,String albumName,String albumArtist,String albumDate, String albumImage,int albumTracks);
    
}
