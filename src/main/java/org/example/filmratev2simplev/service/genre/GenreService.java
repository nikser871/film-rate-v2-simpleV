package org.example.filmratev2simplev.service.genre;

import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.dto.GenreDTO;
import org.example.filmratev2simplev.model.Genre;
import org.springframework.http.HttpHeaders;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreDTO> getGenres();
    Optional<GenreDTO> createGenre(GenreDTO genre);

    Optional<GenreDTO> getGenreById(Long id);
}
