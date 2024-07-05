package com.project.playit.project.Controller;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Service.AudioService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @PostMapping("/upload")
    public ResponseEntity<Audio> uploadUserAudioFile(AudioUploadRequest request){
        return ResponseEntity.ok(audioService.uploadAudioFile(request));
    }
}
