package org.example.filmratev2simplev.mappers;


import org.example.filmratev2simplev.dto.GenreDTO;
import org.example.filmratev2simplev.model.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    GenreDTO genreToGenreDTO(Genre genre);

    Genre genreDTOToGenre(GenreDTO genreDTO);
}
