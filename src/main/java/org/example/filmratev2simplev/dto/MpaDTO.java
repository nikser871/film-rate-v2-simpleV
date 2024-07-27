package org.example.filmratev2simplev.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MpaDTO {

    private Long id;

    @Size(min = 2, max = 7)
    private String name;
}
