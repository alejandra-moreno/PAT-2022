package com.pat2022.pat.service.dto;

public record EpisodeDTO(
    String episodeName,
    String episodePublisher,
    String episodeDescription,
    String episodeImage,
    int episodeTracks
) {}
