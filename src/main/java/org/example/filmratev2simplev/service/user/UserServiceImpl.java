package org.example.filmratev2simplev.service.user;

import org.example.filmratev2simplev.controller.NotFoundException;
import org.example.filmratev2simplev.dto.UserDTO;
import org.example.filmratev2simplev.mappers.UserMapper;
import org.example.filmratev2simplev.model.Film;
import org.example.filmratev2simplev.model.Friendship;
import org.example.filmratev2simplev.model.User;
import org.example.filmratev2simplev.model.UserFilm;
import org.example.filmratev2simplev.repositories.FilmRepository;
import org.example.filmratev2simplev.repositories.UserRepository;
import org.example.filmratev2simplev.storage.user.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository userRep;
    private final FilmRepository filmRep;

    public UserServiceImpl(UserMapper mapper, UserRepository userRep, FilmRepository filmRep) {
        this.mapper = mapper;
        this.userRep = userRep;
        this.filmRep = filmRep;
    }

//
//    public String addFriend(int id1, int id2) {
//        userStorage.addFriend(id1, id2);
//        return "Well done!!!";
//    }
//
//    public String deleteFriend(int id1, int id2) {
//        userStorage.deleteFriend(id1, id2);
//        return "Well delete!!!";
//    }
//
//    public Collection<User> getFriends(int userId) {
//        return userStorage.getFriends(userId);
//    }
//
//    public Collection<User> getCommonFriends(int id1, int id2) {
//        List<User> friendsUser1 = (List<User>) userStorage.getFriends(id1);
//        List<User> friendsUser2 = (List<User>) userStorage.getFriends(id2);
//        System.out.println("sdasdasdasdasd");
//
//        return friendsUser2.stream()
//                .filter(friendsUser1::contains)
//                .toList();
//
//    }


    @Override
    public Optional<UserDTO> createUser(UserDTO userDTO) {
        return Optional.ofNullable(mapper.userToUserDTO(userRep.save(mapper.userDTOToUser(userDTO))));
    }

    @Override
    public Optional<UserDTO> updateUserById(Long id, UserDTO userDTO) {
        AtomicReference<Optional<UserDTO>> user = new AtomicReference<>();

        userRep.findById(id).ifPresentOrElse(
                found -> {
                    found.setName(userDTO.getName());
                    found.setEmail(userDTO.getEmail());
                    found.setLogin(userDTO.getLogin());
                    found.setBirthday(userDTO.getBirthday());
                    user.set(
                            Optional.of(mapper.userToUserDTO(userRep.saveAndFlush(found)))
                    );
                },
                () -> user.set(Optional.empty())
        );

        return user.get();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRep.findAll().stream()
                .map(mapper::userToUserDTO)
                .toList();
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.ofNullable(mapper.userToUserDTO(userRep.findById(id).orElse(null)));
    }

    @Override
    public void likeFilm(Long userId, Long filmId) {
        UserFilm userFilm = new UserFilm();
        User user = userRep.findById(userId).orElseThrow(NotFoundException::new);
        Film film = filmRep.findById(userId).orElseThrow(NotFoundException::new);
        userFilm.add(user, film);
        userRep.saveAndFlush(user);
        filmRep.saveAndFlush(film);
    }

    @Override
    public void deleteLikeFilm(Long userId, Long filmId) {
        userRep.deleteLikeFilm(userId, filmId);
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        Friendship friendship = new Friendship();
        User user1 = userRep.findById(userId).orElseThrow(NotFoundException::new);
        User user2 = userRep.findById(friendId).orElseThrow(NotFoundException::new);
        friendship.add(user1, user2);
        userRep.saveAndFlush(user1);
        userRep.saveAndFlush(user2);
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        userRep.deleteFriend(userId, friendId);
    }

    @Override
    public List<UserDTO> getFriends(Long userId) {
        return userRep.getFriends(userId).stream()
                .map(mapper::userToUserDTO)
                .toList();
    }

    @Override
    public List<UserDTO> getFollowersByFilmId(Long filmId) {
        return userRep.getFollowersByFilmId(filmId).stream()
                .map(mapper::userToUserDTO)
                .toList();
    }

    @Override
    public List<UserDTO> getCommonFriends(Long user1, Long user2) {
        return userRep.getCommonFriends(user1, user2).stream()
                .map(mapper::userToUserDTO)
                .toList();
    }
}
