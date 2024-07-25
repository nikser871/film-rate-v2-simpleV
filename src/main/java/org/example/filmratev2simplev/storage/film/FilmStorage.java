package org.example.filmratev2simplev.storage.film;

import org.example.filmratev2simplev.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {
    Film createFilm(Film film);
    Film updateFilm(Film film);
    Collection<Film> getAllFilms();

    Optional<Integer> getCountOfFollowers(int filmId);

    Optional<Film> getFilmById(int filmId);

}
