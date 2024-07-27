package org.example.filmratev2simplev.repositories;

import org.example.filmratev2simplev.model.Friendship;
import org.example.filmratev2simplev.model.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Friendship.Id> {
}
