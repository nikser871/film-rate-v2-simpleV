package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @NotNull(message = "Name is null")
    @NotBlank(message = "Name is null")
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull(message = "releaseDate is null")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    @NotNull(message = "releaseDate is null")
    private int duration;

    @OneToMany(mappedBy = "film")
    private List<FilmGenre> filmGenres;

    @ManyToOne
    @JoinColumn(name = "mpa_id", nullable = false)
    private Mpa mpa;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<UserFilm> userFilms;


}
