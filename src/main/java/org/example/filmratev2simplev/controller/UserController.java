package org.example.filmratev2simplev.controller;

import org.example.filmratev2simplev.model.User;
import org.example.filmratev2simplev.service.UserService;
import org.example.filmratev2simplev.storage.user.UserStorage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserStorage userStorage;
    private final UserService service;

    @Autowired
    public UserController(UserStorage userStorage, UserService service) {
        this.userStorage = userStorage;
        this.service = service;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userStorage.createUser(user);

    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        return userStorage.updateUser(user);

    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    @PutMapping("/user/{userId}/film/like/{filmId}")
    public String likeFilm(@PathVariable int userId,@PathVariable int filmId) {
        userStorage.likeFilm(userId, filmId);
        return "OK!!!";
    }

    @DeleteMapping("/user/{userId}/film/delete/{filmId}")
    public String deleteFilm(@PathVariable int userId,@PathVariable int filmId) {
        return userStorage.deleteLikeFilm(userId, filmId);
    }

    @PutMapping(value = "{id}/friends/{friendId}")
    public String addFriend(@PathVariable int id, @PathVariable int friendId){
        return service.addFriend(id, friendId);
    }

    @DeleteMapping(value = "{id}/friends/{friendId}")
    public String deleteFriend(@PathVariable int id, @PathVariable int friendId){
        return service.deleteFriend(id, friendId);
    }

    @GetMapping(value = "{id}/friends")
    public Collection<User> getListOfFriends(@PathVariable int id){
        return service.getFriends(id);
    }

    @GetMapping(value = "{id}/friends/common/{otherId}")
    public Collection<User> getCommonFriends(@PathVariable int id, @PathVariable int otherId){
        return service.getCommonFriends(id, otherId);
    }

    @GetMapping (value = "/count/followers/film/{id}")
    public Collection<User> getFollowersByFilmId(@PathVariable int id) {
        return userStorage.getFollowersByFilmId(id);
    }

    @GetMapping (value = "/user/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userStorage.getUserById(id);
    }

}
