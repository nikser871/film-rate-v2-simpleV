package org.example.filmratev2simplev.service.genre;


import org.example.filmratev2simplev.dto.GenreDTO;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreDTO> getGenres();
    Optional<GenreDTO> createGenre(GenreDTO genre);

    Optional<GenreDTO> getGenreById(Long id);
}
