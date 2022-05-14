package com.pat2022.pat.service;

import com.pat2022.pat.model.ArtistModel;

public interface ArtistService {
    Iterable<ArtistModel> getArtist();
    ArtistModel getArtistById(String artistId);
    void deleteArtist(String artistId);
    void createArtist(ArtistModel artist);
}
