package org.example.filmratev2simplev.repositories;


import org.example.filmratev2simplev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
