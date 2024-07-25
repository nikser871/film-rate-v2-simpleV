package org.example.filmratev2simplev.service;

import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.storage.film.FilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private final FilmStorage filmStorage;


    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Collection<Film> getTopFilms(int count) {
        List<Film> list = filmStorage.getAllFilms().stream().toList();
        return list
                .stream()
                .sorted(Comparator.comparing(x -> filmStorage.getCountOfFollowers(x.getId()).orElse(0),
                        Comparator.reverseOrder()))
                .limit(count)
                .toList();
    }




}
