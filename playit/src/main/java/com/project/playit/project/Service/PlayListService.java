package com.project.playit.project.Service;

import com.project.playit.project.Entity.PlayList;

import java.util.UUID;

public interface PlayListService {
    public PlayList createPlayList(String playListName);

    public PlayList getPlayListByID(UUID playListID);
}
