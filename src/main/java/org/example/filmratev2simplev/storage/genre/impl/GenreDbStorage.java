//package org.example.filmratev2simplev.storage.genre.impl;
//
//import org.example.filmratev2simplev.model.Genre;
//import org.example.filmratev2simplev.storage.genre.GenreStorage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Optional;
//
//@Component
//public class GenreDbStorage implements GenreStorage {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Override
//    public Collection<Genre> getGenres() {
//        String sql = "SELECT * FROM genre";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> getGenre(rs));
//    }
//
//    @Override
//    public Optional<Genre> getGenreById(int id) {
//        String sql = "SELECT * FROM genre WHERE id = ?";
//        return Optional.ofNullable(jdbcTemplate.query(sql, (rs, rowNum) -> getGenre(rs), id).get(0));
//    }
//
//
//    public Genre getGenre(ResultSet rs) {
//        try {
//            return new Genre(rs.getInt("id"), rs.getString("name"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//}
