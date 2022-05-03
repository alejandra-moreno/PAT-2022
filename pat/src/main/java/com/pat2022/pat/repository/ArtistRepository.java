package com.pat2022.pat.repository;

import com.pat2022.pat.model.ArtistModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<ArtistModel,String>{
    
    @Query("SELECT * FROM ARTIST WHERE ARTIST.ARTIST_ID = :artistId")
    public Iterable <ArtistModel> getArtistById(String artistId);

    @Modifying
    @Query("INSERT INTO ARTIST (ARTIST_ID, ARTIST_NAME, ARTIST_IMAGE, ARTIST_GENRES, ARTIST_FOLLOWERS) VALUES (:artistId,:artistName,:artistImage,:artistGenres,:artistFollowers)")
    public void createArtist(String artistId,String artistName,String artistImage,String artistGenres,int artistFollowers);

}
