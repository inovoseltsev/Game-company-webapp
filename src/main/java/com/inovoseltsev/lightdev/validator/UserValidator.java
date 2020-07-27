package com.inovoseltsev.lightdev.validator;

import com.inovoseltsev.lightdev.domain.AppUser;
import com.inovoseltsev.lightdev.domain.role.Role;
import com.inovoseltsev.lightdev.domain.state.State;
import com.inovoseltsev.lightdev.service.AppUserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    @Autowired
    private AppUserService userService;

    private String errorMessage;

    public AppUser build(Map<String, String> userParameters) {
        if (userParameters == null) {
            throw new RuntimeException(new NullPointerException("UserParameters are null!"));
        }
        String firstName = userParameters.get("firstName");
        String lastName = userParameters.get("lastName");
        String login = userParameters.get("login");
        String password = userParameters.get("password");
        String repeatedPassword = userParameters.get("repeatedPassword");
        String email = userParameters.get("email");
        if (isUserParametersValid(firstName, lastName, login, password,
                repeatedPassword, email) && isLoginAndEmailUnique(login, email)) {
            if ("".equals(email)) {
                email = null;
            }
            return new AppUser(firstName, lastName, login, password, email,
                    Role.USER, State.ACTIVE);
        } else {
            return null;
        }
    }

    private boolean isUserParametersValid(String firstName,
                                          String lastName,
                                          String login,
                                          String password,
                                          String repeatedPassword,
                                          String email) {
        if ("".equals(firstName) || firstName == null) {
            errorMessage = "Ваше имя введено некорректно";
            return false;
        } else if ("".equals(lastName) || lastName == null) {
            errorMessage = "Ваша фамилия введена некорректно";
            return false;
        } else if ("".equals(login) || login == null) {
            errorMessage = "Ваш логин введен некорректно";
            return false;
        } else if ("".equals(password) || password == null ||
                "".equals(repeatedPassword) || repeatedPassword == null) {
            errorMessage = "Ваш пароль введен некорректно";
            return false;
        } else if (!password.equals(repeatedPassword)) {
            errorMessage = "Пароли не совпадают";
            return false;
        } else if (email != null && email.length() > 0) {
            int dotCounter = 0;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '.' && dotCounter == 1) {
                    errorMessage = "Неверый формат почты";
                    return false;
                }
                dotCounter++;
            }
        }
        return true;
    }

    private boolean isLoginAndEmailUnique(String login, String email) {
        if (userService.findByLogin(login) != null) {
            errorMessage = "Такой логин уже существует";
            return false;
        } else if (userService.findByEmail(email) != null) {
            errorMessage = "Эта почта уже занята";
            return false;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
