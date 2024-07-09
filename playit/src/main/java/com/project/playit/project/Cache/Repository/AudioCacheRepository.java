package com.project.playit.project.Cache.Repository;

import com.project.playit.project.Entity.Audio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AudioCacheRepository extends CrudRepository<Audio, UUID> {
}
