package com.project.playit.project.Cache.Service;

import com.project.playit.project.Entity.Audio;

import java.util.UUID;

public interface AudioCacheService {

    public Audio saveAudioFile(Audio audio);

    public Audio getAudioByID(UUID audioID);

    public void deleteAudioByID(UUID audioID);
}
