package org.example.filmratev2simplev.service.film;

import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.model.Film;
import org.springframework.http.HttpHeaders;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmService {

    Optional<FilmDTO> createFilm(FilmDTO film);
    Optional<FilmDTO> updateFilmById(Long id, FilmDTO film);
    List<FilmDTO> getAllFilms();
    Optional<FilmDTO> getFilmById(Long id);
    List<FilmDTO> getTopFilms(Long count);
}
