package org.example.filmratev2simplev.mappers;


import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.model.Film;
import org.mapstruct.Mapper;

@Mapper
public interface FilmMapper {

    FilmDTO filmToFilmDTO(Film film);

    Film filmDTOToFilm(FilmDTO filmDTO);
}
