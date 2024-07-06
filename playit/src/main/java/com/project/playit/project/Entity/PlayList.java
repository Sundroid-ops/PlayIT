package com.project.playit.project.Entity;

import com.project.playit.Auth.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_playlist")
@Validated
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID playListID;

    @NotNull(message = "playlist name cannot be null")
    @NotEmpty(message = "playlist name cannot be empty")
    @Length(max = 20, message = "playlist name cannot be more than 20 chars")
    private String playListName;

    @ManyToMany(
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
    private List<Audio> audioList;

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

    public void addAudioList(Audio audio){
        if(audioList == null)
            audioList = new LinkedList<>();

        audioList.add(audio);
    }
}
