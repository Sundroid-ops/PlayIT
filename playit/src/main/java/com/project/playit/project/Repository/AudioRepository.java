package com.project.playit.project.Repository;

import com.project.playit.project.Entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AudioRepository extends JpaRepository<Audio, UUID> {
    List<Audio> findAllByAudioNameContainingIgnoreCase(String audioName);
}
