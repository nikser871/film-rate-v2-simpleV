package org.example.filmratev2simplev.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 3)
    private String login;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // '2001-12-12'
//    @DateTimeFormat
    private LocalDate birthday;

    @Email
    private String email;
}
