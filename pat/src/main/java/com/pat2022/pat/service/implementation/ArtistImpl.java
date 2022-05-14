package com.pat2022.pat.service.implementation;

import com.pat2022.pat.model.ArtistModel;
import com.pat2022.pat.repository.ArtistRepository;
import com.pat2022.pat.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArtistImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Iterable<ArtistModel> getArtist() {
        
        return artistRepository.findAll();
    }

    @Override
    public ArtistModel getArtistById(String artistId) {
        
        return artistRepository.findById(artistId).get();
    }

    @Override
    public void deleteArtist(String artistId) {
        artistRepository.deleteById(artistId);
    }

    @Override
    public void createArtist(ArtistModel artist) {
        String artistId = artist.getArtistId();
        String artistName = artist.getArtistName();
        String artistImage = artist.getArtistImage();
        String artistGenres = artist.getArtistGenres();
        int artistFollowers = artist.getArtistFollowers();

        artistRepository.createArtist(artistId, artistName, artistImage, artistGenres, artistFollowers);
        
    }
    
}
