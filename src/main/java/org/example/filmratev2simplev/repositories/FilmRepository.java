package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "select COUNT(*) from UserFilm u where u.film.id = :filmId")
    Long getCountOfFollowers(@Param("filmId") Long filmId);
}
