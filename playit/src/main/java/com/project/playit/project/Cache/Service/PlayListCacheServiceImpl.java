package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PlayListCacheServiceImpl implements PlayListCacheService{

    @Autowired
    private RedisTemplate<String, PlayList> redisTemplate;

    private final static String KEY = "PLAYLIST";

    @Override
    public PlayList savePlayList(PlayList playList) {
        redisTemplate.opsForHash().put(KEY, playList.getPlayListID(), playList);
        //redisTemplate.expire(KEY, 10, TimeUnit.MINUTES);
        return playList;
    }
}
