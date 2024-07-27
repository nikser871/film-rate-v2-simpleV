package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmGenreRepository extends JpaRepository<FilmGenre, FilmGenre.Id> {
}
