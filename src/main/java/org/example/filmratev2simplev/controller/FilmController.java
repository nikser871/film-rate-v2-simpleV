package org.example.filmratev2simplev.controller;


import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.service.film.FilmService;
import org.example.filmratev2simplev.service.film.FilmServiceImpl;
import org.example.filmratev2simplev.storage.film.FilmStorage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private final AppProperties appProperties;
    private final FilmService filmService;

    public FilmController(AppProperties appProperties, FilmService filmService) {
        this.appProperties = appProperties;
        this.filmService = filmService;
    }


    @PostMapping
    public ResponseEntity createFilm(@Valid @RequestBody FilmDTO film) {
        FilmDTO filmDTO = filmService.createFilm(film).orElseThrow(InternalServerError::new);
        return ResponseEntity.created(
            URI.create(appProperties.getBaseUrlFilm() + "/" + filmDTO.getId()))
                .build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<FilmDTO> updateFilmById(@Valid @RequestBody FilmDTO film, @PathVariable Long id) {
        FilmDTO updatedFilm = filmService.updateFilmById(id, film).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedFilm);
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }
//
//
//    @GetMapping (value = "/popular")
//    public Collection<Film> getPopularFilms(@RequestParam(defaultValue = "10") String count){
//        return service.getTopFilms(Integer.parseInt(count));
//    }

    @GetMapping (value = "/film/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Long id) {
        FilmDTO filmDTO = filmService.getFilmById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(filmDTO);
    }







}
