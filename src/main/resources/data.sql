INSERT INTO genre(id, name) VALUES(1, 'thriller');
INSERT INTO genre(id, name) VALUES (2, 'horror');
INSERT INTO mpa(id, name) VALUES (1, 'G');
INSERT INTO "user"(id, name, birthday) VALUES (1, 'Maxim', '2001-12-12');
INSERT INTO "user"(id, name, birthday) VALUES (2, 'Boris', '1999-12-12');
INSERT INTO "user"(id, name, birthday) VALUES (3, 'Valera', '2000-12-12');
INSERT INTO login(id, user_id) VALUES ('max2001', 1);
INSERT INTO login(id, user_id) VALUES ('boris1999', 2);
INSERT INTO login(id, user_id) VALUES ('valera2000', 3);
INSERT INTO email(id, user_id) VALUES ('max@mail.ru', 1);
INSERT INTO email(id, user_id) VALUES ('boris@mail.ru', 2);
INSERT INTO email(id, user_id) VALUES ('valera@mail.ru', 3);
INSERT INTO film(id, name, description, release_date, duration, mpa_id) VALUES (1, 'One at home', 'COOL!!!!', '1990-12-12', 103, 1);
INSERT INTO film(id, name, description, release_date, duration, mpa_id) VALUES (2, 'One at home 2', 'IMPRESSIVE!!!!', '1992-12-11', 120, 1);
INSERT INTO film_genres(film_genre_id, genres_id) VALUES (1, 1);
INSERT INTO film_genres(film_genre_id, genres_id) VALUES (1, 2);
INSERT INTO film_genres(film_genre_id, genres_id) VALUES (2, 2);
INSERT INTO friendship(first_user_id, second_user_id) VALUES (1, 2);
INSERT INTO friendship(first_user_id, second_user_id) VALUES (1, 3);
INSERT INTO friendship(first_user_id, second_user_id) VALUES (2, 3);
INSERT INTO user_film(user_id, film_id) VALUES (1, 1);
INSERT INTO user_film(user_id, film_id) VALUES (1, 2);
INSERT INTO user_film(user_id, film_id) VALUES (2, 2);
INSERT INTO user_film(user_id, film_id) VALUES (3, 1);







