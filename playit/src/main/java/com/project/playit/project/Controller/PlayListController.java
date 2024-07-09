package com.project.playit.project.Controller;

import com.project.playit.project.Entity.PlayList;
import com.project.playit.project.Exception.AccessDeniedException;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Exception.PlayListNotFoundException;
import com.project.playit.project.Service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/playlist")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @PostMapping("/create")
    public ResponseEntity<PlayList> createPlayList(@RequestParam String playListName){
        return ResponseEntity.ok(playListService.createPlayList(playListName));
    }

    @GetMapping("/{playListID}")
    public ResponseEntity<PlayList> getPlayListByID(@PathVariable UUID playListID)
            throws PlayListNotFoundException, AccessDeniedException {
        return ResponseEntity.ok(playListService.getPlayListByID(playListID));
    }

    @PutMapping("/{playListID}")
    public ResponseEntity<PlayList> addAudioFileInPlayList(
            @PathVariable UUID playListID,
            @RequestBody List<UUID> audioIDList) throws PlayListNotFoundException, AudioFileNotFoundException, AccessDeniedException {
        return ResponseEntity.ok(playListService.addAudioFileInPlayList(playListID, audioIDList));
    }

    @DeleteMapping("/{playListID}/song")
    public ResponseEntity<PlayList> removeAudioFileFromPlayList(
            @PathVariable UUID playListID,
            @RequestParam UUID audioID)
            throws PlayListNotFoundException, AudioFileNotFoundException, AccessDeniedException {
        return ResponseEntity.ok(playListService.removeAudioFileFromPlayList(playListID, audioID));
    }

    @DeleteMapping("/{playListID}")
    public ResponseEntity<String> removePlayListByID(@PathVariable UUID playListID)
            throws PlayListNotFoundException{
        playListService.removePlayListByID(playListID);

        return ResponseEntity.ok("PlayList ID " + playListID + " Deleted Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlayList>> getPlayListByName(
            @RequestParam String playListName,
            @RequestParam int page,
            @RequestParam int size) throws PlayListNotFoundException{
        return ResponseEntity.ok(playListService.getPlayListByName(playListName, page, size));
    }
}
