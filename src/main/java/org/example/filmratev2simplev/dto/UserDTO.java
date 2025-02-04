package org.example.filmratev2simplev.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Null
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 3)
    private String login;


//    @Past
//    @Pattern(regexp = "^((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Date of birth can't be before 1900") // '2001-12-12'
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;

    @Email
    private String email;
}
