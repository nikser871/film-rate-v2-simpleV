package org.example.filmratev2simplev.dto;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {

    private Long id;

    @NotNull(message = "Name is null")
    @NotBlank(message = "Name is null")
    private String name;

    private String description;

    @NotNull(message = "releaseDate is null")
    @Past
    @Pattern(regexp = "^((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Date of release can't be before 1900") // '2001-12-12'
    private LocalDate releaseDate;

    @NotNull(message = "releaseDate is null")
    @Positive
    private Long duration;

    @NotNull
    List<Long> genresId;

    @NotNull
    private Long mpaId;

}
