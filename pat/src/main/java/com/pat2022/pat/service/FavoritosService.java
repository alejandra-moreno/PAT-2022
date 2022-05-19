package com.pat2022.pat.service;

import java.util.List;

import com.pat2022.pat.model.FavoritosModel;
import com.pat2022.pat.service.dto.AlbumDTO;
import com.pat2022.pat.service.dto.ArtistDTO;
import com.pat2022.pat.service.dto.EpisodeDTO;
import com.pat2022.pat.service.dto.SongDTO;

public interface FavoritosService {
    
    Iterable<FavoritosModel> getFav();
    FavoritosModel getFavById(String userId, String favId);
    void deleteFav(String userId, String favId);
    void createFav(FavoritosModel fav);

    List<SongDTO> getFavouriteSong();
    List<SongDTO> getFavouriteSongByUser(String userId);

    List<AlbumDTO> getFavouriteAlbum();
    List<AlbumDTO> getFavouriteAlbumByUser(String userId);

    List<EpisodeDTO> getFavouriteEpisode();
    List<EpisodeDTO> getFavouriteEpisodeByUser(String userId);
    
    List<ArtistDTO> getFollowing();
    List<ArtistDTO> getFollowingByUser(String userId);
}
