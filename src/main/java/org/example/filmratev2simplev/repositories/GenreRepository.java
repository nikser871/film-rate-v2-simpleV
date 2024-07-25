package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
