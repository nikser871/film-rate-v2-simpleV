package org.example.filmratev2simplev.dto;


import jakarta.validation.constraints.Null;
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

    @Null
    private Long id;

    @Size(min = 1, max = 7)
    private String name;


}
