package com.project.playit.project.Entity;

import com.project.playit.Auth.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_song")
@Validated
public class Audio implements Serializable {

    @Id
    private UUID audioID;

    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "name cannot be null")
    @Length(max = 20, message = "name cannot be more than 20 characters")
    private String audioName;

    @NotNull(message = "url cannot be null")
    @Column(unique = true)
    private String cloudinary_file_url;

    @NotNull(message = "url cannot be null")
    @Column(unique = true)
    private String cloudinary_file_public_id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private User user_upload;

    @NotNull(message = "genre cannot be null")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull(message = "genre cannot be null")
    private LocalDate releaseDate;
}
