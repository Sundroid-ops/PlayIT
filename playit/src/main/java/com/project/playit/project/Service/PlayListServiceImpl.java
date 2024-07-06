package com.project.playit.project.Service;

import com.project.playit.Auth.Service.CurrentUserService;
import com.project.playit.project.Entity.PlayList;
import com.project.playit.project.Exception.PlayListNotFoundException;
import com.project.playit.project.Repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PlayListServiceImpl implements PlayListService{

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private CurrentUserService currentUserService;

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
}
