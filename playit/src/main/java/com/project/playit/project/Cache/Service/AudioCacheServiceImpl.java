package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.Audio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AudioCacheServiceImpl implements AudioCacheService{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final static String KEY = "AUDIO";

    @Override
    public void saveAudioFile(Audio audio) {
        redisTemplate.opsForHash().put(KEY, audio.getAudioID(), audio);
        redisTemplate.expire(KEY, 10, TimeUnit.MINUTES);
    }

    @Override
    public Audio getAudioByID(UUID audioID) {
        return (Audio) redisTemplate.opsForHash().get(KEY, audioID);
    }

    @Override
    public void deleteAudioByID(UUID audioID) {
        redisTemplate.opsForHash().delete(KEY, audioID);
    }
}
