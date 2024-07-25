package org.example.filmratev2simplev.controller;

import org.example.filmratev2simplev.model.Genre;
import org.example.filmratev2simplev.storage.genre.GenreStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    private final GenreStorage genreStorage;

    @Autowired
    public GenreController(GenreStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    @GetMapping
    public Collection<Genre> getAllGenres() {
        return genreStorage.getGenres();
    }


    @GetMapping("/{id}")
    public Optional<Genre> getGenreById(@PathVariable int id) {
        return genreStorage.getGenreById(id);
    }
}
