package com.project.playit.project.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    public ResponseEntity<String> uploadAudioFile(MultipartFile file, String folderName);
}