package com.project.playit.project.Service;

import com.project.playit.Auth.Service.CurrentUserService;
import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AudioRepository audioRepository;

    @Override
    public Audio uploadAudioFile(AudioUploadRequest request){

        Audio song = Audio.builder()
                .audioID(UUID.randomUUID())
                .audioName(request.getName())
                .file_url(cloudinaryService.uploadAudioFile(request.getFile(), request.getName()))
                .user_upload(currentUserService.getCurrentUser())
                .genre(request.getGenre())
                .releaseDate(LocalDate.now())
                .build();

        return audioRepository.save(song);
    }

    @Override
    public List<Audio> getAudioListFromAudioName(String audioName) throws AudioFileNotFoundException {
        List<Audio> audioList = audioRepository.findAllByAudioNameContainingIgnoreCase(audioName);
    public List<Audio> getAudioListFromAudioName(String audioName, int page, int size) throws AudioFileNotFoundException {

        if(audioList.isEmpty())
            throw new AudioFileNotFoundException("Audio Not Found");

        return audioList;
    }
}
