package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PlayListCacheServiceImpl implements PlayListCacheService{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final static String KEY = "PLAYLIST";

    @Override
    public PlayList savePlayList(PlayList playList) {
        redisTemplate.opsForHash().put(KEY, playList.getPlayListID(), playList);
        //redisTemplate.expire(KEY, 10, TimeUnit.MINUTES);
        return playList;
    }

    @Override
    public PlayList getPlayListByID(UUID playListID) {
        return (PlayList) redisTemplate.opsForHash().get(KEY, playListID);
    }

    @Override
    public void deletePlayListByID(UUID playListID) {
        redisTemplate.opsForHash().delete(KEY, playListID);
    }
}
