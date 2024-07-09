package com.project.playit.project.Service;

import com.project.playit.Auth.Service.CurrentUserService;
import com.project.playit.project.Entity.Audio;
import com.project.playit.project.Entity.PlayList;
import com.project.playit.project.Exception.AccessDeniedException;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Exception.PlayListNotFoundException;
import com.project.playit.project.Repository.AudioRepository;
import com.project.playit.project.Repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PlayListServiceImpl implements PlayListService{

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private AudioRepository audioRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AudioService audioService;

    @Override
    public PlayList createPlayList(String playListName) {
        PlayList playList = PlayList.builder()
                .playListID(UUID.randomUUID())
                .playListName(playListName)
                .creationDate(LocalDate.now())
                .user_playList(currentUserService.getCurrentUser())
                .build();

        return playListRepository.save(playList);
    }

    @Override
    public PlayList getPlayListByID(UUID playListID) throws PlayListNotFoundException{
        return playListRepository.findById(playListID)
                .orElseThrow(() -> new PlayListNotFoundException("PlayList Not Found for ID : " + playListID));
    }

    @Override
    public PlayList addAudioFileInPlayList(UUID playListID, List<UUID> audioListID)
            throws PlayListNotFoundException, AudioFileNotFoundException,  AccessDeniedException{

        PlayList playList = getPlayListByID(playListID);

        if(!playList.getUser_playList().getUsername()
                .equals(currentUserService.getCurrentUser().getUsername()))
            throw new AccessDeniedException("You do not have permission to perform this request on this content");

        List<Audio> audioList = audioRepository.findAllById(audioListID);

        if (audioList.isEmpty())
            throw new AudioFileNotFoundException("Audio Not Found");

        playList.addAudioList(audioList);

        return playListRepository.save(playList);
    }

    @Override
    public PlayList removeAudioFileFromPlayList(UUID playListID, UUID audioID)
            throws PlayListNotFoundException, AudioFileNotFoundException, AccessDeniedException {
        PlayList playList = getPlayListByID(playListID);

        if(!playList.getUser_playList().getUsername()
                .equals(currentUserService.getCurrentUser().getUsername()))
            throw new AccessDeniedException("You do not have permission to perform this request on this content");

        if(playList.getAudioList().isEmpty())
            throw new AccessDeniedException("PlayList is empty, You do not have permission to perform this request on this content");

        Audio audio = audioService.getAudioByID(audioID);

        playList.removeAudioFromAudioList(audio);

        return playListRepository.save(playList);
    }

    @Override
    public void removePlayListByID(UUID playListID) throws PlayListNotFoundException, AccessDeniedException {
        PlayList playList = getPlayListByID(playListID);

        if(!playList.getUser_playList().getUsername()
                .equals(currentUserService.getCurrentUser().getUsername()))
            throw new AccessDeniedException("You do not have permission to perform this request on this content");


        playListRepository.delete(playList);
    }

    @Override
    public List<PlayList> getPlayListByName(String playListName, int page, int size) throws PlayListNotFoundException {
        List<PlayList> playLists = playListRepository.
                findAllByPlayListNameContainingIgnoreCase(playListName, PageRequest.of(page, size));

        if(playLists.isEmpty())
            throw new PlayListNotFoundException("No playlist found");

        return playLists;
    }

    @Override
    public List<PlayList> getAllplayList(int page, int size) {
        return playListRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
