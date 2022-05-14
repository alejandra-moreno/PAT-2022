package com.pat2022.pat.controller;

import com.pat2022.pat.model.EpisodeModel;
import com.pat2022.pat.service.EpisodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EpisodeController {
    
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/episode")
    public ResponseEntity<Iterable<EpisodeModel>> retriveEpisode(){
        Iterable<EpisodeModel> response = episodeService.getEpisode();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/episode/{episodeId}")
    public EpisodeModel getEpisode(@PathVariable String episodeId){
        return episodeService.getEpisodeById(episodeId);
    }

    @PostMapping("/episode")
    public ResponseEntity<String> createEpisodeById(@RequestBody EpisodeModel episode, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            episodeService.createEpisode(episode);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("episode/{episodeId}")
    public ResponseEntity<EpisodeModel> deleteEpisode(@PathVariable String episodeId){
        episodeService.deleteEpisode(episodeId);
        return ResponseEntity.noContent().build();
    }

}
