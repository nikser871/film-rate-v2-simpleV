package org.example.filmratev2simplev.controller;

import lombok.RequiredArgsConstructor;
import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.UserDTO;
import org.example.filmratev2simplev.service.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final AppProperties appProperties;
    private final UserService userService;

    private static final String LIKE_FILM = "/user/{userId}/film/like/{filmId}";
    private static final String DELETE_FILM = "/user/{userId}/film/delete/{filmId}";
    private static final String FRIEND = "/{id}/friends/{friendId}";
    private static final String FRIENDS = "/{id}/friends";
    private static final String COMMON_FRIENDS = "/{id}/friends/common/{otherId}";
    private static final String FILM_FOLLOWERS = "/count/followers/film/{id}";
    private static final String USER_ID = "/user/{id}";

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDTO user) {
        UserDTO createdUser = userService.createUser(user).orElseThrow(InternalServerError::new);
        return ResponseEntity.created(
                URI.create(appProperties.getBaseUrlUser() + "/" + createdUser.getId())
        ).build();

    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable Long id) {
        UserDTO updatedUser = userService.updateUserById(id, user).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedUser);


    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping(LIKE_FILM)
    public ResponseEntity<String> likeFilm(@PathVariable Long userId, @PathVariable Long filmId) {
        userService.likeFilm(userId, filmId);
        return ResponseEntity.ok(
                "LIKED!!!"
        );
    }

    @DeleteMapping(DELETE_FILM)
    public ResponseEntity<String> deleteFilm(@PathVariable Long userId, @PathVariable Long filmId) {
        userService.deleteLikeFilm(userId, filmId);
        return ResponseEntity.ok(
                "DELETED FILM FROM FAVOURITES!!!"
        );
    }

    @PutMapping(FRIEND)
    public ResponseEntity<String> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        userService.addFriend(id, friendId);

        return ResponseEntity.ok(
                "You have added a new Friend!!!"
        );
    }

    @DeleteMapping(FRIEND)
    public ResponseEntity<String> deleteFriend(@PathVariable Long id, @PathVariable Long friendId) {
        userService.deleteFriend(id, friendId);
        return ResponseEntity.ok(
                "You've deleted an old Friend!!!"
        );
    }

    @GetMapping(FRIENDS)
    public ResponseEntity<List<UserDTO>> getListOfFriends(@PathVariable Long id) {
        return ResponseEntity.ok(
                userService.getFriends(id)
        );
    }

    @GetMapping(COMMON_FRIENDS)
    public ResponseEntity<List<UserDTO>> getCommonFriends(@PathVariable Long id, @PathVariable Long otherId) {
        return ResponseEntity.ok(
                userService.getCommonFriends(id, otherId)
        );
    }

    @GetMapping(FILM_FOLLOWERS)
    public ResponseEntity<List<UserDTO>> getFollowersByFilmId(@PathVariable Long id) {
        return ResponseEntity.ok(
                userService.getFollowersByFilmId(id)
        );
    }

    @GetMapping(USER_ID)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id).orElseThrow(NotFoundException::new));
    }

}
