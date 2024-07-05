package com.project.playit.project.Service;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Exception.AudioFileNotFoundException;

import java.util.List;

public interface AudioService {
    public Audio uploadAudioFile(AudioUploadRequest request);

    public List<Audio> getAudioListFromAudioName(String audioName, int page, int size) throws AudioFileNotFoundException;
}
