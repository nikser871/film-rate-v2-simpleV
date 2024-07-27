package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.mapping.Join;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "film_genre")
@Immutable
public class FilmGenre {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    private Film film;

    @ManyToOne
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
