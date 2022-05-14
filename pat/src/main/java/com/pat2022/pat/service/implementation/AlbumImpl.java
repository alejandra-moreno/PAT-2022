package com.pat2022.pat.service.implementation;

import com.pat2022.pat.model.AlbumModel;
import com.pat2022.pat.repository.AlbumRepository;
import com.pat2022.pat.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public Iterable<AlbumModel> getAlbum() {
        
        return albumRepository.findAll();
    }

    @Override
    public AlbumModel getAlbumById(String albumId) {
        
        return albumRepository.findById(albumId).get();
    }

    @Override
    public void deleteAlbum(String albumId) {
        albumRepository.deleteById(albumId);
        
    }

    @Override
    public void createAlbum(AlbumModel album) {
        String albumId = album.getAlbumId();
        String albumName = album.getAlbumName();
        String albumArtist = album.getAlbumArtist();
        String albumDate = album.getAlbumDate();
        String albumImage = album.getAlbumImage();
        int albumTracks = album.getAlbumTracks();

        albumRepository.createAlbum(albumId, albumName, albumArtist, albumDate, albumImage, albumTracks);
        
    }
    
}
