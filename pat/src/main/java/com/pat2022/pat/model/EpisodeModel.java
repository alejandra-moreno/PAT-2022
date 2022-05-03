package com.pat2022.pat.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("EPISODE")

public class EpisodeModel {

    @Id
    private String episodeId;

    private String episodeName;
    private String episodePublisher;
    private String episodeDescription;
    private String episodeImage;
    private int episodeTracks;

    public String getEpisodeId() {
        return this.episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeName() {
        return this.episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodePublisher() {
        return this.episodePublisher;
    }

    public void setEpisodePublisher(String episodePublisher) {
        this.episodePublisher = episodePublisher;
    }

    public String getEpisodeDescription() {
        return this.episodeDescription;
    }

    public void setEpisodeDescription(String episodeDescription) {
        this.episodeDescription = episodeDescription;
    }

    public String getEpisodeImage() {
        return this.episodeImage;
    }

    public void setEpisodeImage(String episodeImage) {
        this.episodeImage = episodeImage;
    }

    public int getEpisodeTracks() {
        return this.episodeTracks;
    }

    public void setEpisodeTracks(int episodeTracks) {
        this.episodeTracks = episodeTracks;
    }

}
