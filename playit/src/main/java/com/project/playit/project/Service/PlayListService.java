package com.project.playit.project.Service;

import com.project.playit.project.Entity.PlayList;
import com.project.playit.project.Exception.AccessDeniedException;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import com.project.playit.project.Exception.PlayListNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PlayListService {
    public PlayList createPlayList(String playListName);

    public PlayList getPlayListByID(UUID playListID) throws PlayListNotFoundException;

    public PlayList addAudioFileInPlayList(UUID playListID, List<UUID> audioListID)
            throws PlayListNotFoundException, AudioFileNotFoundException, AccessDeniedException;

    public PlayList removeAudioFileFromPlayList(UUID playListID, UUID audioID)
            throws PlayListNotFoundException, AudioFileNotFoundException, AccessDeniedException;
}
