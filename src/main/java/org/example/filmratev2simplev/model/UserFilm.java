package org.example.filmratev2simplev.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"user", "film"})
@Table(name = "user_film")
@Entity
public class UserFilm {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    private Film film;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "film_id")
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
