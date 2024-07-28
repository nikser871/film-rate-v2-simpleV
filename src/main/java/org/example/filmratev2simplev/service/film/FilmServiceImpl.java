package org.example.filmratev2simplev.service.film;

import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.controller.NotFoundException;
import org.example.filmratev2simplev.dto.FilmDTO;
import org.example.filmratev2simplev.mappers.FilmMapper;
import org.example.filmratev2simplev.model.*;
import org.example.filmratev2simplev.repositories.FilmGenreRepository;
import org.example.filmratev2simplev.repositories.FilmRepository;
import org.example.filmratev2simplev.repositories.GenreRepository;
import org.example.filmratev2simplev.repositories.MpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FilmServiceImpl implements FilmService {


    private final FilmRepository filmRep;
    private final FilmMapper mapper;
    private final GenreRepository genreRep;
    private final MpaRepository mpaRep;
    private final FilmGenreRepository filmGenreRep;

    public FilmServiceImpl(AppProperties appProperties, FilmRepository filmRep, FilmMapper mapper, GenreRepository genreRep, MpaRepository mpaRep, FilmGenreRepository filmGenreRep) {
        this.filmRep = filmRep;
        this.mapper = mapper;
        this.genreRep = genreRep;
        this.mpaRep = mpaRep;
        this.filmGenreRep = filmGenreRep;
    }

    @Override
    public Optional<FilmDTO> createFilm(FilmDTO film) {

        Film film1 = mapper.filmDTOToFilm(film);


        List<FilmGenre> filmGenres = new ArrayList<>();
        film1.setFilmGenres(new ArrayList<>());
        film1.setUserFilms(new ArrayList<>());
        film1.setMpa(mpaRep.findById(film.getMpaId()).orElseThrow(NotFoundException::new));
        filmRep.save(film1);

        film.getGenresId()
                .forEach(x -> {
                    Genre genre = genreRep.findById(x).orElseThrow(NotFoundException::new);
                    FilmGenre filmGenre = new FilmGenre();
                    filmGenre.add(film1, genre);
                    filmGenres.add(filmGenre);

                });


        film1.setFilmGenres(filmGenres);
        filmGenreRep.saveAll(filmGenres);
        return Optional.of(getFilmDTO(filmRep.findById(film1.getId()).orElseThrow(NotFoundException::new)));

    }

    @Override
    public Optional<FilmDTO> updateFilmById(Long id, FilmDTO film) {
        AtomicReference<Optional<FilmDTO>> atomicReference = new AtomicReference<>();

        filmRep.findById(id).ifPresentOrElse(found -> {
                    found.setName(film.getName());
                    found.setDescription(film.getDescription());
                    found.setDuration(film.getDuration());
                    found.setReleaseDate(film.getReleaseDate());

                    List<FilmGenre> filmGenres = new ArrayList<>();
                    Mpa mpa = mpaRep.findById(film.getMpaId()).orElseThrow(NotFoundException::new);
                    filmGenreRep.deleteAll(found.getFilmGenres());


                    film.getGenresId().forEach(x -> {
                        FilmGenre filmGenre = new FilmGenre();
                        filmGenre.add(found, genreRep.findById(x).orElseThrow(NotFoundException::new));
                        filmGenres.add(filmGenre);
                        filmGenreRep.save(filmGenre);
                    });

                    found.setFilmGenres(filmGenres);
                    found.setMpa(mpa);

                    atomicReference.set(Optional.of(
                            getFilmDTO(filmRep.saveAndFlush(found))
                    ));
                },
                () -> atomicReference.set(Optional.empty())
        );
        return atomicReference.get();
    }

    @Override
    public List<FilmDTO> getAllFilms() {


        return filmRep.findAll().stream()
                .map(this::getFilmDTO)
                .toList();
    }



    @Override
    public Optional<FilmDTO> getFilmById(Long id) {
        Film film = filmRep.findById(id).orElseThrow(NotFoundException::new);

        return Optional.of(getFilmDTO(film));
    }

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
                .map(this::getFilmDTO)
                .toList();
    }

    private FilmDTO getFilmDTO(Film x) {

        if (x == null) throw new NotFoundException();

        FilmDTO dto = mapper.filmToFilmDTO(x);
        List<Long> list = new ArrayList<>();
        dto.setMpaId(x.getMpa().getId());
        x.getFilmGenres().forEach(y -> list.add(y.getGenre().getId()));
        dto.setGenresId(list);
        return dto;
    }


}
