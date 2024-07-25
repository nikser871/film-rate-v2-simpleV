package org.example.filmratev2simplev.storage.genre;

import org.example.filmratev2simplev.model.Genre;

import java.util.Collection;
import java.util.Optional;


public interface GenreStorage {
    Collection<Genre> getGenres();
    Optional<Genre> getGenreById(int id);
}
