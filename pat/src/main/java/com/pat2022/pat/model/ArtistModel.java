package com.pat2022.pat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("ARTIST")


public class ArtistModel {
    
    @Id
    private String artistId;

    private String artistName;
    private String artistImage;
    private String artistGenres;
    private int artistFollowers;

    public String getArtistId() {
        return this.artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImage() {
        return this.artistImage;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }

    public String getArtistGenres() {
        return this.artistGenres;
    }

    public void setArtistGenres(String artistGenres) {
        this.artistGenres = artistGenres;
    }

    public int getArtistFollowers() {
        return this.artistFollowers;
    }

    public void setArtistFollowers(int artistFollowers) {
        this.artistFollowers = artistFollowers;
    }

}
