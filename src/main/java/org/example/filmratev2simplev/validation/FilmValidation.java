package org.example.filmratev2simplev.validation;

import org.example.filmratev2simplev.exception.FilmException;

import java.time.LocalDate;

public class FilmValidation {
    public static void checkName(String name) throws FilmException{
        if (name == null) {
            throw new FilmException("Name is null");
        }
    }
    public static void checkDescription(String description) throws FilmException{
        if (description.length() > 200) {
            throw new FilmException("Description contains symbols more than 200");
        }
    }
    public static void checkDataRelease(LocalDate date) throws FilmException{
        if (!date.isAfter(LocalDate.of(1895, 12, 28))) {
            throw new FilmException("Date of release can't be before 1895.12.28");
        }
    }

    public static void checkDuration(int time) throws FilmException{
        if (time < 0) {
            throw new FilmException("Duration can't be negative");
        }
    }
}
