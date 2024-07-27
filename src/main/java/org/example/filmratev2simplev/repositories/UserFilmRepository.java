package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFilmRepository extends JpaRepository<UserFilm, UserFilm.Id> {
}
