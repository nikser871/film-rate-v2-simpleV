//package org.example.filmratev2simplev.storage.user.impl;
//
//import org.example.filmratev2simplev.exception.UserException;
//import org.example.filmratev2simplev.model.User;
//import org.example.filmratev2simplev.storage.user.UserStorage;
//import org.example.filmratev2simplev.validation.UserValidation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Optional;
//
//@Component
//@Primary
//public class UserDbStorage implements UserStorage {
//
//
//    @Autowired
//    private final JdbcTemplate jdbcTemplate;
//
//
//    public UserDbStorage(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Override
//    public User createUser(User user) {
//
//        UserValidation.checkBirthday(user.getBirthday());
//        UserValidation.checkEmail(user.getEmail());
//        UserValidation.checkLogin(user.getLogin());
//
//
//        String sql = "INSERT INTO \"user\"(id, name, birthday) VALUES (?, ?, ?)";
//        String sql2 = "INSERT INTO email(id, user_id) VALUES(?, ?)";
//        String sql3 = "INSERT INTO login(id, user_id) VALUES(?, ?)";
//
//        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getBirthday());
//        jdbcTemplate.update(sql2, user.getEmail(), user.getId());
//        jdbcTemplate.update(sql3, user.getLogin(), user.getId());
//
//        return user;
//
//    }
//
//    @Override
//    public User updateUser(User user) {
//        UserValidation.checkBirthday(user.getBirthday());
//
//        String sql = "UPDATE \"user\" set name = ?, birthday = ? WHERE id = ?";
//        String sql2 = "UPDATE email set id = ? WHERE user_id = ?";
//        String sql3 = "UPDATE login set id = ? WHERE user_id = ?";
//
//
//        jdbcTemplate.update(sql, user.getName(), user.getBirthday(), user.getId());
//        jdbcTemplate.update(sql2, user.getEmail(), user.getId());
//        jdbcTemplate.update(sql3, user.getLogin(), user.getId());
//
//        return user;
//    }
//
//    @Override
//    public Collection<User> getAllUsers() {
//        String sql = "SELECT * FROM \"user\"";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs));
//    }
//
//    @Override
//    public void likeFilm(int userId, int filmId) {
//        String sql = "INSERT INTO user_film(user_id, film_id) VALUES(?, ?)";
//        int update = jdbcTemplate.update(sql, userId, filmId);
//        if (update != 1) {
//            throw new UserException("Вы уже лайкали этот фильм!");
//        }
//    }
//
//    @Override
//    public String deleteLikeFilm(int userId, int filmId) {
//        String sql = "DELETE FROM user_film WHERE user_id = ? AND film_id = ?";
//        int update = jdbcTemplate.update(sql, userId, filmId);
//        return update == 1 ? "SUCCESS" : "FAILED";
//    }
//
//    @Override
//    public String addFriend(int firstId, int secondId) {
//        String sql = "INSERT INTO friendship(first_user_id, second_user_id) VALUES (?, ?)";
//        int update = jdbcTemplate.update(sql, firstId, secondId);
//        if (update != 1) {
//            throw new UserException("Данный пользователь уже находится в списке друзей!");
//        }
//        return "SUCCESSFUL!!!";
//    }
//
//    @Override
//    public String deleteFriend(int firstId, int secondId) {
//        String sql = "DELETE FROM friendship WHERE first_user_id = ? AND second_user_id = ?";
//        int update = jdbcTemplate.update(sql, firstId, secondId);
//        if (update != 1) {
//            throw new UserException("Произошла ошибка!!!");
//        }
//        return null;
//    }
//
//    @Override
//    public Collection<User> getFriends(int userId) {
//        String sql = "SELECT * FROM \"user\" u JOIN friendship f ON u.id = f.second_user_id WHERE f.first_user_id = ?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs), userId);
//    }
//
//    @Override
//    public Collection<User> getFollowersByFilmId(int filmId) {
//        String sql = "SELECT * FROM \"user\" u JOIN user_film uf ON u.id = uf.user_id WHERE uf.film_id = ?";
//        return jdbcTemplate.query(sql, (rs, row) -> new UserDbStorage(jdbcTemplate).makeUser(rs), filmId);
//    }
//
//    @Override
//    public Optional<User> getUserById(int userId) {
//        String sql = "SELECT * FROM \"user\" WHERE id = ?";
//        return Optional.of(jdbcTemplate.query(sql, (rs, row) -> makeUser(rs), userId).get(0));
//    }
//
//    public String getEmail(int userId) {
//        String sql = "SELECT id FROM email WHERE user_id = ?";
//        return jdbcTemplate.queryForObject(sql, String.class, userId);
//    }
//
//    public String getLogin(int userId) {
//        String sql = "SELECT id FROM login WHERE user_id = ?";
//        return jdbcTemplate.queryForObject(sql, String.class, userId);
//    }
//
//
//
//    public User makeUser(ResultSet rs) {
//        User user;
//
//        try {
//            user = new User(rs.getInt("id"),
//                    rs.getString("name"),
//                    getLogin(rs.getInt("id")),
//                    rs.getDate("birthday").toLocalDate(),
//                    getEmail(rs.getInt("id")));
//
//            return user;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
