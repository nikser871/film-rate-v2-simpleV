package org.example.filmratev2simplev.repositories;


import org.example.filmratev2simplev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Modifying
//    @Query(value = "insert into user_film (user_id, film_id) values (?1, ?2)", nativeQuery = true)
//    void likeFilm(Long userId, Long filmId);
//
    @Modifying
    @Query(value = "delete from UserFilm u WHERE u.user.id = :userID and u.film.id = :filmId")
    void deleteLikeFilm(@Param("userId") Long userId, @Param("filmId") Long filmId);
//
//    @Modifying
//    @Query(value = "INSERT INTO friendship(user_id1, user_id2) VALUES (?1, ?2)", nativeQuery = true)
//    void addFriend(Long userId, Long friendId);
//
    @Modifying
    @Query(value = "delete Friendship f WHERE f.user1.id = :userId and f.user2.id = :friendId")
    void deleteFriend(@Param("userId") Long userId,@Param("friendId") Long friendId);

    @Query(value = "select f.user2 from Friendship f where f.user1.id = :userId")
    List<User> getFriends(@Param("userId") Long userId);

    @Query(value = "select uf.user from UserFilm uf where uf.film.id = :filmID")
    List<User> getFollowersByFilmId(@Param("filmId") Long filmId);
//
    @Query(value = "select us1.user2 from Friendship us1" +
            " where us1.user1.id = :user1 " +
            "and " +
            "us1.user2.id in (select us2.user2.id from Friendship us2 where us2.user1.id = :user2)")
    List<User> getCommonFriends(@Param("user1") Long user1, @Param("user2") Long user2);

}
