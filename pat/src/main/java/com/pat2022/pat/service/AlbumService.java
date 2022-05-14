package com.pat2022.pat.service;

import com.pat2022.pat.model.AlbumModel;

public interface AlbumService {
    Iterable<AlbumModel> getAlbum();
    AlbumModel getAlbumById(String albumId);
    void deleteAlbum(String albumId);
    void createAlbum(AlbumModel album);
}
