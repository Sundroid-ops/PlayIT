package com.project.playit.project.Service;

import com.project.playit.Auth.Service.CurrentUserService;
import com.project.playit.project.Cache.Service.AudioCacheService;
import com.project.playit.project.DTO.AudioUploadRequest;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Entity.Genre;
import com.project.playit.project.Exception.AccessDeniedException;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AudioRepository audioRepository;

    @Autowired
    private AudioCacheService audioCacheService;

    @Override
    public Audio uploadAudioFile(AudioUploadRequest request) {
        try {
            Map<String, String> uploadFileData =  cloudinaryService.uploadAudioFile(request.getFile(), request.getName());

            Audio audio = Audio.builder()
                .audioID(UUID.randomUUID())
                .audioName(request.getName())
                .cloudinary_file_url(uploadFileData.get("file_url"))
                .cloudinary_file_public_id(uploadFileData.get("public_id"))
                .user_upload(currentUserService.getCurrentUser())
                .genre(request.getGenre())
                .releaseDate(LocalDate.now())
                .build();

            audioRepository.save(audio);

            return audioCacheService.saveAudioFile(audio);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred while performing the operation\", e");
        }
    }

    @Override
    public List<Audio> getAudioListFromAudioName(String audioName, int page, int size)
            throws AudioFileNotFoundException {
        List<Audio> audioList = audioRepository.findAllByAudioNameContainingIgnoreCase(audioName, PageRequest.of(page, size));

        if(audioList.isEmpty())
            throw new AudioFileNotFoundException("Audio Not Found");

        return audioList;
    }

    @Override
    public Audio getAudioByID(UUID audioID)
            throws AudioFileNotFoundException{
        Audio audioCache = audioCacheService.getAudioByID(audioID);

        if(audioCache != null)
            return audioCache;

        Audio audio =  audioRepository.findById(audioID)
            .orElseThrow((() -> new AudioFileNotFoundException("Audio Not Found for ID : " + audioID)));

        return audioCacheService.saveAudioFile(audio);
    }

    @Override
    public void deleteAudioByID(UUID audioID)
            throws AudioFileNotFoundException, AccessDeniedException {
            Audio audio = getAudioByID(audioID);

            if(!audio.getUser_upload().getUsername()
                .equals(currentUserService.getCurrentUser().getUsername()))
                    throw new AccessDeniedException("You do not have permission to perform this request on this content");

            try{
                cloudinaryService.deleteAudioFile(audio.getCloudinary_file_public_id());

                audioRepository.delete(audio);

            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("An unexpected error occurred while performing the operation\", e");
            }
    }

    @Override
    public Audio updateAudioByID(UUID audioID, String audioName, Genre genre)
            throws AudioFileNotFoundException, AccessDeniedException{

        if(audioName.isEmpty() && genre == null)
            throw new AccessDeniedException("At least one of the fields must be non-empty");

        Audio audio = getAudioByID(audioID);

        if(!audio.getUser_upload().getUsername()
                .equals(currentUserService.getCurrentUser().getUsername()))
            throw new AccessDeniedException("You do not have permission to perform this request on this content");

        if(!audioName.isEmpty())
            audio.setAudioName(audioName);

        if(genre != null)
            audio.setGenre(genre);

        try {
            audioRepository.save(audio);

            return audioCacheService.saveAudioFile(audio);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred while performing the operation", e);
        }
    }

    @Override
    public List<Audio> getAllAudioFile(int page, int size) {
        return audioRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
