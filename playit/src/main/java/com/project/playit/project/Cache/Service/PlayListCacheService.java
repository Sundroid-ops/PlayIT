package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.PlayList;

import java.util.UUID;

public interface PlayListCacheService {

    public PlayList savePlayList(PlayList playList);

    public PlayList getPlayListByID(UUID playListID);

    public void deletePlayListByID(UUID playListID);
}
