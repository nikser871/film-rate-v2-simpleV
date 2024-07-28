package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"genre", "film"})
@Table(name = "film_genre")
@Immutable
public class FilmGenre {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "genre_id", insertable=false, updatable=false)
    private Genre genre;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "film_id")
        private Long filmId;

        @Column(name = "genre_id")
        private Long genreId;

    }
    
    public void add(Film film, Genre genre) {
        this.genre = genre;
        this.film = film;
        genre.getFilmGenreList().add(this);
        film.getFilmGenres().add(this);
        this.id.setFilmId(film.getId());
        this.id.setGenreId(genre.getId());
    }

}
