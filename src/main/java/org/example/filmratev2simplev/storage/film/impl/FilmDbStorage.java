package org.example.filmratev2simplev.storage.film.impl;

import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.storage.film.FilmStorage;
import org.example.filmratev2simplev.validation.FilmValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Primary
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film createFilm(Film film) {

        FilmValidation.checkName(film.getName());
        FilmValidation.checkDescription(film.getDescription());
        FilmValidation.checkDataRelease(film.getReleaseDate());
        FilmValidation.checkDuration(film.getDuration());

        String sql = "INSERT INTO film(id, name, description, release_date, duration, " +
                "mpa_id) VALUES (?, ?, ?, ?, ?, ?)";


        jdbcTemplate.update(sql, film.getId(), film.getName(),film.getDescription(),
                film.getReleaseDate(), film.getDuration(), film.getMpaId());



        film.getGenresId().forEach(x -> addFilmGenres(film.getId(), x));



        return film;
    }

    @Override
    public Film updateFilm(Film film) {


        FilmValidation.checkName(film.getName());
        FilmValidation.checkDescription(film.getDescription());
        FilmValidation.checkDataRelease(film.getReleaseDate());
        FilmValidation.checkDuration(film.getDuration());


        String sql = "UPDATE film set name = ?, description = ?, release_date = ?," +
                " duration = ?, " +
                "mpa_id = ? WHERE id = ?";

        String sql2 = "INSERT INTO film_genres(film_genre_id, genres_id) VALUES (?, ?)";

        clearFilmGenres(film.getId());


        jdbcTemplate.update(sql, film.getName(),film.getDescription(),
                film.getReleaseDate(), film.getDuration(),
                film.getMpaId(), film.getId());

        film.getGenresId().forEach(x -> addFilmGenres(film.getId(), x));


        return film;
    }

    @Override
    public Collection<Film> getAllFilms() {
        String sql = "SELECT * FROM film";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeFilm(rs));

    }




    public void addFilmGenres(int filmId, int genreId) {
        String sql = "INSERT INTO film_genres(film_genre_id, genres_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, filmId, genreId);
    }

    public void clearFilmGenres(int filmId) {
        String sql = "DELETE FROM film_genres WHERE film_genre_id = ?";
        jdbcTemplate.update(sql, filmId);
    }

    public Collection<Integer> getGenresId(int filmId) {
        String sql = "SELECT genres_id FROM film_genres WHERE film_genre_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("genres_id"), filmId);
    }

    public Optional<Integer> getCountOfFollowers(int filmId) {
        String sql = "SELECT COUNT(*) as count FROM user_film WHERE film_id = ?";
        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, Integer.class, filmId)).describeConstable();
    }

    @Override
    public Optional<Film> getFilmById(int filmId) {
        String sql = "SELECT * FROM film WHERE id = ?";
        return Optional.of(jdbcTemplate.query(sql, (rs, row) -> makeFilm(rs), filmId).get(0));
    }


    private Film makeFilm(ResultSet rs) {
        Film film;


        try {
            List<Integer> genresId = getGenresId(rs.getInt("id")).stream().toList();
            film = new Film(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("release_date").toLocalDate(),
                    rs.getInt("duration"),
                    genresId,
                    rs.getInt("mpa_id"));
            return film;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
