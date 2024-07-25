package org.example.filmratev2simplev.service;

import org.example.filmratev2simplev.model.User;
import org.example.filmratev2simplev.storage.user.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {


    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    public String addFriend(int id1, int id2) {
        userStorage.addFriend(id1, id2);
        return "Well done!!!";
    }

    public String deleteFriend(int id1, int id2) {
        userStorage.deleteFriend(id1, id2);
        return "Well delete!!!";
    }

    public Collection<User> getFriends(int userId) {
        return userStorage.getFriends(userId);
    }

    public Collection<User> getCommonFriends(int id1, int id2) {
        List<User> friendsUser1 = (List<User>) userStorage.getFriends(id1);
        List<User> friendsUser2 = (List<User>) userStorage.getFriends(id2);
        System.out.println("sdasdasdasdasd");

        return friendsUser2.stream()
                .filter(friendsUser1::contains)
                .toList();

    }


}
