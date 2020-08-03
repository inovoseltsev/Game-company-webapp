package com.inovoseltsev.lightdev.validator;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.domain.role.Role;
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
        String firstName = userParameters.get("firstName");
        String lastName = userParameters.get("lastName");
        String login = userParameters.get("login");
        String password = userParameters.get("password");
        String email = userParameters.get("email");
        if (checkEmailAndLoginAreFree(login, email)) {
            if ("".equals(email)) {
                email = null;
            }
            return new AppUser(firstName, lastName, login, password, email,
                    Role.USER   );
        } else {
            return null;
        }
    }

    private boolean checkEmailAndLoginAreFree(String login, String email) {
        if (userService.findByLogin(login) != null) {
            errorMessage = "Login is already created!";
            return false;
        } else if (userService.findByEmail(email) != null) {
            errorMessage = "Email is already used!";
            return false;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
