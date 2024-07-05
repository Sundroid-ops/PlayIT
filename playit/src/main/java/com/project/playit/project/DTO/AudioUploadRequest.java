package com.project.playit.project.DTO;

import com.project.playit.project.Entity.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Validated
public class AudioUploadRequest {
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Length(max = 20, message = "name cannot have more than 20 characters")
    private String name;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private MultipartFile file;

    @NotNull(message = "genre cannot be null")
    @NotBlank(message = "genre cannot be empty")
    @Enumerated(EnumType.STRING)
    private Genre genres;
}
