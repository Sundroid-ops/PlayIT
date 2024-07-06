package com.project.playit.project.Repository;

import com.project.playit.project.Entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, UUID> {
}
