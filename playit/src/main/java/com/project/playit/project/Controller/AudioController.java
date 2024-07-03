package com.project.playit.project.Controller;

import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/song")
public class AudioController {

    @Autowired
    private AudioService songService;

    @GetMapping("/")
    public ResponseEntity<Audio> test(){
        return ResponseEntity.ok(songService.uploadAudioFile());
    }
}
