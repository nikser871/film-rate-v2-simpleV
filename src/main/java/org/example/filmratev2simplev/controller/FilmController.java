package org.example.filmratev2simplev.controller;


import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.service.FilmService;
import org.example.filmratev2simplev.storage.film.FilmStorage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private final FilmStorage filmStorage;
    private final FilmService service;

    @Autowired
    public FilmController(FilmStorage filmStorage, FilmService service) {
        this.filmStorage = filmStorage;
        this.service = service;
    }


    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        return filmStorage.createFilm(film);
    }


    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmStorage.updateFilm(film);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }


    @GetMapping (value = "/popular")
    public Collection<Film> getPopularFilms(@RequestParam(defaultValue = "10") String count){
        return service.getTopFilms(Integer.parseInt(count));
    }

    @GetMapping (value = "/film/{id}")
    public Optional<Film> getUserById(@PathVariable int id) {
        return filmStorage.getFilmById(id);
    }







}
