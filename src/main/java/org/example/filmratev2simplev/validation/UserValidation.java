package org.example.filmratev2simplev.validation;

import org.example.filmratev2simplev.exception.UserException;
import org.example.filmratev2simplev.model.User;

import java.time.LocalDate;


public class UserValidation {
    public static void checkEmail(String email) throws UserException {
        if (email == null) {
            throw new UserException("Email is null");
        } else if (!email.contains("@")) {
            throw new UserException("Email must contain '@'");
        }
    }
    public static void checkLogin(String login) throws UserException {
        if (login == null) {
            throw new UserException("Login is null");
        } else if (login.contains(" ")) {
            throw new UserException("Login mustn't contain whitespaces");
        }
    }

    public static void checkName(User user){
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
    }

    public static void checkBirthday(LocalDate birthday) throws UserException {
        if (birthday.isAfter(LocalDate.now())) {
            throw new UserException("Birthday date can't be in the future");
        }
    }
}
