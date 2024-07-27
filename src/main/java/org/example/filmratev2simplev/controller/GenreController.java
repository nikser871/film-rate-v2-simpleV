package org.example.filmratev2simplev.controller;

import jakarta.validation.Valid;
import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.GenreDTO;
import org.example.filmratev2simplev.model.Genre;
import org.example.filmratev2simplev.service.genre.GenreService;
import org.example.filmratev2simplev.service.genre.GenreServiceImpl;
import org.example.filmratev2simplev.storage.genre.GenreStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    private final GenreService service;
    private final AppProperties appProperties;

    public GenreController(GenreService service, AppProperties appProperties) {
        this.service = service;
        this.appProperties = appProperties;
    }

    @PostMapping
    public ResponseEntity createGenre(@RequestBody @Valid GenreDTO dto) throws URISyntaxException {
        GenreDTO savedGenre = service.createGenre(dto).orElseThrow(InternalServerError::new);
        return ResponseEntity.created(
                URI.create(appProperties.getBaseUrlGenre() + "/" + savedGenre.getId())
        )
                .build();
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genreDTOS = service.getGenres();
        return ResponseEntity.ok(genreDTOS);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        GenreDTO genreDTO = service.getGenreById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(genreDTO);
    }
}
