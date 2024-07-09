package com.project.playit.project.Service;

import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Entity.Genre;
import com.project.playit.project.Exception.AccessDeniedException;
import com.project.playit.project.Exception.AudioFileNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AudioService {
    public Audio uploadAudioFile(AudioUploadRequest request);

    public List<Audio> getAudioListFromAudioName(String audioName, int page, int size) throws AudioFileNotFoundException;

    public Audio getAudioByID(UUID audioID) throws AudioFileNotFoundException;

    public void deleteAudioByID(UUID audioID) throws AudioFileNotFoundException, AccessDeniedException;

    public Audio updateAudioByID(UUID audioID, String audioName, Genre genre) throws AudioFileNotFoundException, AccessDeniedException;

    public List<Audio> getAllAudioFile(int page, int size);
}
