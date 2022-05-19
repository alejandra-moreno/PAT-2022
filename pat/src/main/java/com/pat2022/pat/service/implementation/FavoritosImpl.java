package com.pat2022.pat.service.implementation;

import java.util.List;


import com.pat2022.pat.model.FavoritosModel;
import com.pat2022.pat.repository.FavoritosRepository;
import com.pat2022.pat.service.FavoritosService;
import com.pat2022.pat.service.dto.AlbumDTO;
import com.pat2022.pat.service.dto.ArtistDTO;
import com.pat2022.pat.service.dto.EpisodeDTO;
import com.pat2022.pat.service.dto.SongDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FavoritosImpl implements FavoritosService{

    @Autowired
    private FavoritosRepository favoritosRepository;

    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Iterable<FavoritosModel> getFav() {
        
        return favoritosRepository.findAll();
    }

    @Override
    public FavoritosModel getFavById(String userId, String favId) {
        return favoritosRepository.getFavById(userId, favId);
    }

    @Override
    public void deleteFav(String userId, String favId) {
        favoritosRepository.deleteFav(userId, favId);
    }

    @Override
    public void createFav(FavoritosModel fav) {
       String userId = fav.getUserId();
       String favId = fav.getFavId();
       String tipo = fav.getTipo();

       favoritosRepository.createFav(userId, favId, tipo);
        
    }

    //FUNCION CANCIONES

    @Override
    public List<SongDTO> getFavouriteSong(){
        String query =
        """
            SELECT * FROM ((FAVOURITE
            INNER JOIN SONG ON FAVOURITE.FAV_ID = SONG.SONG_ID)
            INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID)    
            WHERE FAVOURITE.TIPO = 'cancion';
        """;

        List<SongDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new SongDTO(
                rs.getString("SONG_NAME"),
                rs.getString("SONG_ARTIST"),
                rs.getString("SONG_ALBUM"),
                rs.getInt("SONG_DURATION")
            )
        );
        return joinList;
    }

    @Override
    public List<SongDTO> getFavouriteSongByUser(String userId) {
        String query =
        "SELECT * FROM ((FAVOURITE INNER JOIN SONG ON FAVOURITE.FAV_ID = SONG.SONG_ID) INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID) WHERE FAVOURITE.TIPO = 'cancion' AND FAVOURITE.USER_ID = "+"\'"+userId+"\'";

        List<SongDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new SongDTO(
                rs.getString("SONG_NAME"),
                rs.getString("SONG_ARTIST"),
                rs.getString("SONG_ALBUM"),
                rs.getInt("SONG_DURATION")
            )
        );
        return joinList;
    }

    @Override
    public List<AlbumDTO> getFavouriteAlbum() {
        String query =
        """
            SELECT * FROM ((FAVOURITE
            INNER JOIN ALBUM ON FAVOURITE.FAV_ID = ALBUM.ALBUM_ID)
            INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID)    
            WHERE FAVOURITE.TIPO = 'album';
        """;

        List<AlbumDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new AlbumDTO(
                rs.getString("ALBUM_NAME"),
                rs.getString("ALBUM_ARTIST"),
                rs.getString("ALBUM_DATE"),
                rs.getString("ALBUM_IMAGE"),
                rs.getInt("ALBUM_TRACKS")
            )
        );
        return joinList;
    }

    @Override
    public List<AlbumDTO> getFavouriteAlbumByUser(String userId) {
        String query =
        "SELECT * FROM ((FAVOURITE INNER JOIN ALBUM ON FAVOURITE.FAV_ID = ALBUM.ALBUM_ID) INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID) WHERE FAVOURITE.TIPO = 'album' AND FAVOURITE.USER_ID = "+"\'"+userId+"\'";

        List<AlbumDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new AlbumDTO(
                rs.getString("ALBUM_NAME"),
                rs.getString("ALBUM_ARTIST"),
                rs.getString("ALBUM_DATE"),
                rs.getString("ALBUM_IMAGE"),
                rs.getInt("ALBUM_TRACKS")
            )
        );
        return joinList;
    }

    @Override
    public List<EpisodeDTO> getFavouriteEpisode() {
        String query =
        """
            SELECT * FROM ((FAVOURITE
            INNER JOIN EPISODE ON FAVOURITE.FAV_ID = EPISODE.EPISODE_ID)
            INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID)    
            WHERE FAVOURITE.TIPO = 'podcast';
        """;

        List<EpisodeDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new EpisodeDTO(
                rs.getString("EPISODE_NAME"),
                rs.getString("EPISODE_PUBLISHER"),
                rs.getString("EPISODE_DESCRIPTION"),
                rs.getString("EPISODE_IMAGE"),
                rs.getInt("EPISODE_TRACKS")
            )
        );
        return joinList;
    }

    @Override
    public List<EpisodeDTO> getFavouriteEpisodeByUser(String userId) {
        String query =
        "SELECT * FROM ((FAVOURITE INNER JOIN EPISODE ON FAVOURITE.FAV_ID = EPISODE.EPISODE_ID) INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID) WHERE FAVOURITE.TIPO = 'podcast' AND FAVOURITE.USER_ID = "+"\'"+userId+"\'";

        List<EpisodeDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new EpisodeDTO(
                rs.getString("EPISODE_NAME"),
                rs.getString("EPISODE_PUBLISHER"),
                rs.getString("EPISODE_DESCRIPTION"),
                rs.getString("EPISODE_IMAGE"),
                rs.getInt("EPISODE_TRACKS")
            )
        );
        return joinList;
    }

    @Override
    public List<ArtistDTO> getFollowing() {
        String query =
        """
            SELECT * FROM ((FAVOURITE
            INNER JOIN ARTIST ON FAVOURITE.FAV_ID = ARTIST.ARTIST_ID)
            INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID)    
            WHERE FAVOURITE.TIPO = 'artista';
        """;

        List<ArtistDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new ArtistDTO(
                rs.getString("ARTIST_NAME"),
                rs.getString("ARTIST_IMAGE"),
                rs.getString("ARTIST_GENRES"),
                rs.getInt("ARTIST_FOLLOWERS")
            )
        );
        return joinList;
    }

    @Override
    public List<ArtistDTO> getFollowingByUser(String userId) {
        String query =
        "SELECT * FROM ((FAVOURITE INNER JOIN ARTIST ON FAVOURITE.FAV_ID = ARTIST.ARTIST_ID) INNER JOIN USER ON FAVOURITE.USER_ID = USER.USER_ID) WHERE FAVOURITE.TIPO = 'artista' AND FAVOURITE.USER_ID = "+"\'"+userId+"\'";

        List<ArtistDTO> joinList = jdbcTemplate.query(
            query, 
            (rs,rowNum) ->
            new ArtistDTO(
                rs.getString("ARTIST_NAME"),
                rs.getString("ARTIST_IMAGE"),
                rs.getString("ARTIST_GENRES"),
                rs.getInt("ARTIST_FOLLOWERS")
            )
        );
        return joinList;
    }

    
    
}
