package org.example.filmratev2simplev.storage.user;

import org.example.filmratev2simplev.model.User;

import java.util.Collection;
import java.util.Optional;


public interface UserStorage {
    User createUser(User user);
    User updateUser(User user);
    Collection<User> getAllUsers();

    void likeFilm(int userId, int filmId);
    String deleteLikeFilm(int userId, int filmId);

    String addFriend(int firstId, int secondId);

    String deleteFriend(int firstId, int secondId);

    Collection<User> getFriends(int userId);

    Collection<User> getFollowersByFilmId(int filmId);

    Optional<User> getUserById(int userId);



}
