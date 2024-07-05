package com.project.playit.project.Entity;

import com.project.playit.Auth.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_song")
@Validated
public class Audio {

    @Id
    private UUID audioID;

    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "name cannot be null")
    @Length(max = 20, message = "name cannot be more than 20 characters")
    private String audioName;

    @NotNull(message = "url cannot be null")
    @Column(unique = true)
    private String file_url;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private User user_upload;

    @NotNull(message = "genre cannot be null")
    @NotBlank(message = "genre cannot be empty")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull(message = "genre cannot be null")
    @NotBlank(message = "genre cannot be empty")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
}
