package com.pat2022.pat.service.implementation;

import com.pat2022.pat.model.EpisodeModel;
import com.pat2022.pat.repository.EpisodeRepository;
import com.pat2022.pat.service.EpisodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeImpl implements EpisodeService{

    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public Iterable<EpisodeModel> getEpisode() {
        
        return episodeRepository.findAll();
    }

    @Override
    public EpisodeModel getEpisodeById(String episodeId) {
        
        return episodeRepository.findById(episodeId).get();
    }

    @Override
    public void deleteEpisode(String episodeId) {
        episodeRepository.deleteById(episodeId);
        
    }

    @Override
    public void createEpisode(EpisodeModel episode) {
        String episodeId = episode.getEpisodeId();
        String episodeName = episode.getEpisodeName();
        String episodePublisher = episode.getEpisodePublisher();
        String episodeDescription = episode.getEpisodeDescription();
        String episodeImage = episode.getEpisodeImage();
        int episodeTracks = episode.getEpisodeTracks();

        episodeRepository.createEpisode(episodeId, episodeName, episodePublisher, episodeDescription, episodeImage, episodeTracks);
        
    }
    
}
