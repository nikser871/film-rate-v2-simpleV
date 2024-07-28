package org.example.filmratev2simplev.service.genre;

import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.GenreDTO;
import org.example.filmratev2simplev.mappers.GenreMapper;
import org.example.filmratev2simplev.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper mapper;
    private final GenreRepository genreRep;

    @Autowired
    public GenreServiceImpl(GenreMapper mapper, GenreRepository genreRep, AppProperties appProperties) {
        this.mapper = mapper;
        this.genreRep = genreRep;
    }

    @Override
    public List<GenreDTO> getGenres() {
        return genreRep.findAll().stream()
                .map(mapper::genreToGenreDTO)
                .toList();
    }

    @Override
    public Optional<GenreDTO> createGenre(GenreDTO genre) {
        return Optional.ofNullable(mapper.genreToGenreDTO(genreRep.save(mapper.genreDTOToGenre(genre))));
    }

    @Override
    public Optional<GenreDTO> getGenreById(Long id) {
        return Optional.ofNullable(
                mapper.genreToGenreDTO(genreRep.findById(id).orElse(null))
        );
    }
}
