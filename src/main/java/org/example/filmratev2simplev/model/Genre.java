package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "name")
@ToString(exclude = "filmGenreList")
@Builder
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "genre", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FilmGenre> filmGenreList = new ArrayList<>();

}
