package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
