package com.inovoseltsev.lightdev.controller.rest;

import com.inovoseltsev.lightdev.domain.OAuth2GoogleUser;
import com.inovoseltsev.lightdev.exception.UserNotFoundException;
import com.inovoseltsev.lightdev.service.OAuth2GoogleUserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("googleUsers")
public class GoogleUserController {

    @Autowired
    private OAuth2GoogleUserService userService;

    @GetMapping
    public List<OAuth2GoogleUser> getUsers() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public OAuth2GoogleUser getUserById(@PathVariable String id) {
        return Optional.ofNullable(userService.findById(id))
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping("/email/{email}")
    public OAuth2GoogleUser getUserByEmail(@PathVariable String email) {
        return Optional.ofNullable(userService.findByEmail(email))
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("{id}")
    public OAuth2GoogleUser updateUserWithId(@RequestBody OAuth2GoogleUser user,
                                             @PathVariable String id) {
        return Optional.ofNullable(userService.findById(id)).map(googleUser -> {
            googleUser.setFirstName(user.getFirstName());
            googleUser.setLastName(user.getLastName());
            return googleUser;
        }).orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("/email/{email}")
    public OAuth2GoogleUser updateUserWithEmail(@RequestBody OAuth2GoogleUser user,
                                                @PathVariable String email) {
        return Optional.ofNullable(userService.findByEmail(email)).map(googleUser -> {
            googleUser.setFirstName(user.getFirstName());
            googleUser.setLastName(user.getLastName());
            return googleUser;
        }).orElseThrow(UserNotFoundException::new);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        OAuth2GoogleUser user = getUserById(id);
        userService.delete(user);
    }
}
