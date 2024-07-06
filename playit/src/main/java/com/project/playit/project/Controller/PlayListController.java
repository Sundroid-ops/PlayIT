package com.project.playit.project.Controller;

import com.project.playit.project.Entity.PlayList;
import com.project.playit.project.Service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlist")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @PostMapping("/create")
    public ResponseEntity<PlayList> createPlayList(@RequestParam String playListName){
        return ResponseEntity.ok(playListService.createPlayList(playListName));
    }
}
