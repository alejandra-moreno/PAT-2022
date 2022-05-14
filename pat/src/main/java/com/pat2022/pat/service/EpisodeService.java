package com.pat2022.pat.service;

import com.pat2022.pat.model.EpisodeModel;

public interface EpisodeService {
    
    Iterable<EpisodeModel> getEpisode();
    EpisodeModel getEpisodeById(String episodeId);
    void deleteEpisode(String episodeId);
    void createEpisode(EpisodeModel episode);
    
}
