package org.example.filmratev2simplev.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String login;
    private LocalDate birthday;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserFilm> userFilm;

}
