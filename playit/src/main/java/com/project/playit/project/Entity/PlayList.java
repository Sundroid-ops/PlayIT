package com.project.playit.project.Entity;

import com.project.playit.Auth.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_playlist")
@Validated
public class PlayList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID playListID;

    @NotNull(message = "playlist name cannot be null")
    @NotEmpty(message = "playlist name cannot be empty")
    @Length(max = 20, message = "playlist name cannot be more than 20 chars")
    private String playListName;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "audio_playlist_map",
            joinColumns = @JoinColumn(
                    name = "playlist_id",
                    referencedColumnName = "playListID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "audio_id",
                    referencedColumnName = "audioID"
            )
    )
    private Set<Audio> audioSet;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private User user_playList;

    @NotNull(message = "creation date cannot be null")
    private LocalDate creationDate;


    public void addAudioSet(List<Audio> audioList){
        audioSet.addAll(audioList);
    }

    public void removeAudioFromSet(Audio audio){
        audioSet.remove(audio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return Objects.equals(playListID, playList.playListID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playListID);
    }
}
