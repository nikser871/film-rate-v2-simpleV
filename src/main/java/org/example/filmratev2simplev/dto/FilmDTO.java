package org.example.filmratev2simplev.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {

    @NotNull(message = "Name is null")
    @NotBlank(message = "Name is null")
    private String name;

    private String description;

    @NotNull(message = "releaseDate is null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // '2001-12-12'
    private LocalDate releaseDate;

    @NotNull(message = "releaseDate is null")
    @Positive
    private int duration;

}
