package org.example.filmratev2simplev.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_film")
@Entity
public class UserFilm {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Data
    @AllArgsConstructor
    static class Id {

        @Column(name = "user_id", nullable = false)
        private Long userId;

        @Column(name = "film_id", nullable = false)
        private Long filmId;

    }

    public void add(User user, Film film) {
        this.user = user;
        this.film = film;
        user.getUserFilm().add(this);
        film.getUserFilms().add(this);
        this.id.setFilmId(film.getId());
        this.id.setUserId(user.getId());

    }
}
