package com.project.playit.project.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    public Map<String, String> uploadAudioFile(MultipartFile file, String folderName) throws IOException;

    public void deleteAudioFile(String cloudinaryFilePublicID) throws IOException;
}
