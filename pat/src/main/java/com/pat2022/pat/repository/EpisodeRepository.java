package com.pat2022.pat.repository;
import com.pat2022.pat.model.EpisodeModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EpisodeRepository extends CrudRepository<EpisodeModel,String>{
    @Query("SELECT * FROM EPISODE WHERE EPISODE.EPISODE_ID = :episodeId")
    public Iterable <EpisodeModel> getEpisodeById(String episodeId);

    @Modifying
    @Query("INSERT INTO EPISODE (EPISODE_ID,EPISODE_NAME,EPISODE_PUBLISHER,EPISODE_DESCRIPTION,EPISODE_IMAGE,EPISODE_TRACKS) VALUES ( :episodeId, :episodeName, :episodePublisher, :episodeDescription, :episodeImage, :episodeTracks)")
    public void createEpisode(String episodeId, String episodeName, String episodePublisher, String episodeDescription, String episodeImage, int episodeTracks);
}
