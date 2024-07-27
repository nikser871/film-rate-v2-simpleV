package org.example.filmratev2simplev.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {

    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;
}
