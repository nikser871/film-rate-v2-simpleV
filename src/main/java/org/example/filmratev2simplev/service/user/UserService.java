package org.example.filmratev2simplev.service.user;

import org.example.filmratev2simplev.dto.UserDTO;


import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> createUser(UserDTO userDTO);

    Optional<UserDTO> updateUserById(Long id, UserDTO userDTO);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    void likeFilm(Long userId, Long filmId);

    void deleteLikeFilm(Long userId, Long filmId);

    void addFriend(Long userId, Long friendId);

    void deleteFriend(Long userId, Long friendId);

    List<UserDTO> getFriends(Long userId);

    List<UserDTO> getFollowersByFilmId(Long filmId);

    List<UserDTO> getCommonFriends(Long user1, Long user2);


}
