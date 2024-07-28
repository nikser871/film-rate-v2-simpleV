package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"filmGenres", "userFilms"})
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

//    @NotNull(message = "Name is null")
//    @NotBlank(message = "Name is null")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

//    @NotNull(message = "releaseDate is null")
    @Column(nullable = false)
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private LocalDate releaseDate;

    @Column(nullable = false)
//    @NotNull(message = "releaseDate is null")
//    @Positive
    private Long duration;

    @Builder.Default
    @OneToMany(mappedBy = "film", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<FilmGenre> filmGenres = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mpa_id", nullable = false)
    private Mpa mpa;

    @Builder.Default
    @OneToMany(mappedBy = "film", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<UserFilm> userFilms = new ArrayList<>();


}
