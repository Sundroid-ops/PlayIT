package com.project.playit.project.Service;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;

public interface AudioService {
    public Audio uploadAudioFile(AudioUploadRequest request);
}
