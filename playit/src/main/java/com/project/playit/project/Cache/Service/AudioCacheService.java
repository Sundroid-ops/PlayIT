package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.Audio;

import java.util.UUID;

public interface AudioCacheService {

    public void saveAudioFile(Audio audio);

    public Audio getAudioByID(UUID audioID);
}
