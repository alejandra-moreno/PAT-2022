package com.pat2022.pat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ALBUM")

public class AlbumModel {
    
    @Id
    private String albumId;

    private String albumName;
    private String albumArtist;
    private String albumDate;
    private String albumImage;
    private int albumTracks;

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumDate() {
        return this.albumDate;
    }

    public void setAlbumDate(String albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumImage() {
        return this.albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public int getAlbumTracks() {
        return this.albumTracks;
    }

    public void setAlbumTracks(int albumTracks) {
        this.albumTracks = albumTracks;
    }

    public AlbumModel() {
    }
}
