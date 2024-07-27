package org.example.filmratev2simplev.config;


import org.example.filmratev2simplev.model.*;
import org.example.filmratev2simplev.repositories.FilmRepository;
import org.example.filmratev2simplev.repositories.GenreRepository;
import org.example.filmratev2simplev.repositories.MpaRepository;
import org.example.filmratev2simplev.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
@EnableConfigurationProperties({AppProperties.class})
public class AppConfig {

    @Bean
    public AppProperties appProperties() {
        return new AppProperties();
    }

    @Bean
    public CommandLineRunner commandLineRunner(GenreRepository genreRepository,
                                               FilmRepository filmRepository,
                                               MpaRepository  mpaRepository,
                                               UserRepository userRepository) {
        return args -> {

            Genre genre1 = Genre.builder().name("thriller").build();
            Genre genre2 = Genre.builder().name("horror").build();
            Mpa mpa = Mpa.builder().name("G").build();
            genreRepository.save(genre1);
            genreRepository.save(genre2);
            mpaRepository.save(mpa);

            User user1 = User.builder()
                    .name("Maxim")
                    .email("max@mail.ru")
                    .login("max2001")
                    .birthday(LocalDate.of(2001, 12, 12)).build();


            User user2 = User.builder()
                    .name("Boris")
                    .email("boris@mail.ru")
                    .login("boris1999")
                    .birthday(LocalDate.of(2001, 12, 12)).build();


            User user3 = User.builder()
                    .name("Maxim")
                    .email("valera@mail.ru")
                    .login("valera2000")
                    .birthday(LocalDate.of(2001, 12, 12)).build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            Film film1 = Film.builder()
                    .name("One at home")
                    .description("COOL!!!!")
                    .duration(103L)
                    .releaseDate(LocalDate.of(1999, 12, 12))
                    .build();

            film1.setMpa(mpa);

            Film film2 = Film.builder()
                    .name("One at home 2")
                    .description("IMPRESSIVE!!!!")
                    .duration(120L)
                    .releaseDate(LocalDate.of(1992, 12, 11))
                    .build();

            film2.setMpa(mpa);

            filmRepository.save(film1);
            filmRepository.save(film2);

            FilmGenre filmGenre1 = new FilmGenre();
            FilmGenre filmGenre2 = new FilmGenre();
            FilmGenre filmGenre3 = new FilmGenre();

            filmGenre1.add(film1, genre1);
            filmGenre2.add(film1, genre2);
            filmGenre3.add(film2, genre2);





            Friendship friendship1 = new Friendship();
            Friendship friendship2 = new Friendship();
            Friendship friendship3 = new Friendship();

            friendship1.add(user1, user2);
            friendship2.add(user1, user3);
            friendship3.add(user2, user3);


            UserFilm userFilm1 = new UserFilm();
            UserFilm userFilm2 = new UserFilm();
            UserFilm userFilm3 = new UserFilm();
            UserFilm userFilm4 = new UserFilm();

            userFilm1.add(user1, film1);
            userFilm2.add(user1, film2);
            userFilm3.add(user2, film2);
            userFilm4.add(user3, film1);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            filmRepository.save(film1);
            filmRepository.save(film2);

            genreRepository.save(genre1);
            genreRepository.save(genre2);


        };




//        -- INSERT INTO user_film(user_id, film_id) VALUES (1, 1);
//        -- INSERT INTO user_film(user_id, film_id) VALUES (1, 2);
//        -- INSERT INTO user_film(user_id, film_id) VALUES (2, 2);
//        -- INSERT INTO user_film(user_id, film_id) VALUES (3, 1);
    }

}
