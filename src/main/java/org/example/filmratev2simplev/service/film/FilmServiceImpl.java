package org.example.filmratev2simplev.service.film;

import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.controller.NotFoundException;
import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.mappers.FilmMapper;
import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.model.FilmGenre;
import org.example.filmratev2simplev.model.Genre;
import org.example.filmratev2simplev.model.UserFilm;
import org.example.filmratev2simplev.repositories.FilmRepository;
import org.example.filmratev2simplev.repositories.GenreRepository;
import org.example.filmratev2simplev.repositories.MpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FilmServiceImpl implements FilmService {

    private final AppProperties appProperties;

    private final FilmRepository filmRep;
    private final FilmMapper mapper;
    private final GenreRepository genreRep;
    private final MpaRepository mpaRep;

    public FilmServiceImpl(AppProperties appProperties, FilmRepository filmRep, FilmMapper mapper, GenreRepository genreRep, MpaRepository mpaRep) {
        this.appProperties = appProperties;
        this.filmRep = filmRep;
        this.mapper = mapper;
        this.genreRep = genreRep;
        this.mpaRep = mpaRep;
    }

    @Override
    public Optional<FilmDTO> createFilm(FilmDTO film) {

        Film film1 = mapper.filmDTOToFilm(film);

        List<FilmGenre> filmGenres = new ArrayList<>();
        film1.setFilmGenres(new ArrayList<>());
        film1.setUserFilms(new ArrayList<>());
        film1.setMpa(mpaRep.findById(film.getId()).orElseThrow(NotFoundException::new));

        film.getGenresId()
                .forEach(x -> {
                    Genre genre = genreRep.findById(x).orElseThrow(NotFoundException::new);
                    FilmGenre filmGenre = new FilmGenre();
                    filmGenre.add(film1, genre);
                    filmGenres.add(filmGenre);
                });

        film1.setFilmGenres(filmGenres);

        return Optional.ofNullable(mapper.filmToFilmDTO(filmRep.save(film1)));

    }

    @Override
    public Optional<FilmDTO> updateFilmById(Long id, FilmDTO film) {
        AtomicReference<Optional<FilmDTO>> atomicReference = new AtomicReference<>();

        filmRep.findById(id).ifPresentOrElse(found -> {
            found.setName(film.getName());
            found.setDescription(film.getDescription());
            found.setDuration(film.getDuration());
            found.setReleaseDate(film.getReleaseDate());
            found.getFilmGenres().clear();

            film.getGenresId().forEach(x -> {
                                FilmGenre filmGenre = new FilmGenre();
                                filmGenre.setFilm(found);
                                filmGenre.setGenre(genreRep.findById(x).orElseThrow(NotFoundException::new));
                            });

            atomicReference.set(Optional.of(
                mapper.filmToFilmDTO(filmRep.saveAndFlush(found))
            ));
        },
                () -> atomicReference.set(Optional.empty())
        );
        return atomicReference.get();
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRep.findAll().stream()
                .map(mapper::filmToFilmDTO)
                .toList();
    }

    @Override
    public Optional<FilmDTO> getFilmById(Long id) {
        return Optional.ofNullable(mapper.filmToFilmDTO(filmRep.findById(id).orElse(null)));
    }
//    @Autowired
//    private final FilmStorage filmStorage;
//
//
//    public FilmServiceImpl(FilmStorage filmStorage) {
//        this.filmStorage = filmStorage;
//    }
//
    public List<FilmDTO> getTopFilms(Long count) {
        List<Film> list = filmRep.findAll();
        return list
                .stream()
                .sorted(
                        Comparator.comparing(x -> filmRep.getCountOfFollowers(x.getId()),
                        Comparator.reverseOrder())
                )
                .limit(count)
                .map(mapper::filmToFilmDTO)
                .toList();
    }


}
