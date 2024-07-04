package com.project.playit.project.Controller;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Service.AudioService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/song")
public class AudioController {

    @Autowired
    private AudioService songService;

    @PostMapping("/upload")
    public ResponseEntity<Audio> test(AudioUploadRequest request){
        return ResponseEntity.ok(songService.uploadAudioFile(request));
    }
}
