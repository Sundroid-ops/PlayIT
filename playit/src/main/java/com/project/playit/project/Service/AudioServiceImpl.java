package com.project.playit.project.Service;

import com.project.playit.Auth.Service.CurrentUserService;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AudioRepository songRepository;

    @Override
    public Audio uploadAudioFile(){

        Audio song = Audio.builder()
                .songID(UUID.randomUUID())
                .Name("how to do it")
                .file_url("done it")
                .user_upload(currentUserService.getCurrentUser())
                .build();

        return songRepository.save(song);
    }
}
