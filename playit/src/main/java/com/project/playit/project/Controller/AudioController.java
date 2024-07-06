package com.project.playit.project.Controller;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/song")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @PostMapping("/upload")
    public ResponseEntity<Audio> uploadUserAudioFile(AudioUploadRequest request) {
        return ResponseEntity.ok(audioService.uploadAudioFile(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<Audio>> getAudioListByAudioName(
            @RequestParam(name = "name") String audioName,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
            ) throws AudioFileNotFoundException {
        return ResponseEntity.ok(audioService.getAudioListFromAudioName(audioName, page, size));
    }

    @GetMapping("/{audioID}")
    public ResponseEntity<Audio> getAudioByID(@PathVariable UUID audioID)
            throws AudioFileNotFoundException{
        return ResponseEntity.ok(audioService.getAudioByID(audioID));
    }

    @DeleteMapping("/{audioID}")
    public ResponseEntity<String> deleteAudioByID(@PathVariable UUID audioID)
            throws AudioFileNotFoundException, AccessDeniedException {
        audioService.deleteAudioByID(audioID);
        return ResponseEntity.ok("Audio ID " + audioID + " Deleted Successfully");
    }
}
