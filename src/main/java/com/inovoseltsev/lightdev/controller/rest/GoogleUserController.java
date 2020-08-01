package com.inovoseltsev.lightdev.controller.rest;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.userfinder.UserServiceFinder;
import com.inovoseltsev.lightdev.exception.UserNotFoundException;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private GoogleUserService userService;

    @Autowired
    @Qualifier("googleUserFinderById")
    private UserServiceFinder finderById;

    @Autowired
    @Qualifier("googleUserFinderByEmail")
    private UserServiceFinder finderByEmail;

    @GetMapping
    public List<GoogleUser> getUsers() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public GoogleUser getUserById(@PathVariable String id) {
        return getUser(finderById, id);
    }

    @GetMapping("/email/{email}")
    public GoogleUser getUserByEmail(@PathVariable String email) {
        return getUser(finderByEmail, email);
    }

    @PutMapping("{id}")
    public GoogleUser updateUserById(@RequestBody GoogleUser user,
                                     @PathVariable String id) {
        return updateUser(finderById, id, user);
    }

    @PutMapping("/email/{email}")
    public GoogleUser updateUserByEmail(@RequestBody GoogleUser user,
                                        @PathVariable String email) {
        return updateUser(finderByEmail, email, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(getUser(finderById, id));
    }

    private GoogleUser getUser(UserServiceFinder userServiceFinder, String param) {
        return (GoogleUser) Optional.ofNullable(userServiceFinder.findUser(param))
                .orElseThrow(UserNotFoundException::new);
    }

    private GoogleUser updateUser(UserServiceFinder userServiceFinder,
                                  String uniqParam, GoogleUser updatedUser) {
        return Optional.ofNullable((GoogleUser) userServiceFinder.findUser(uniqParam))
                .map(googleUser -> {
                    googleUser.setFirstName(updatedUser.getFirstName());
                    googleUser.setLastName(updatedUser.getLastName());
                    userService.update(googleUser);
                    return googleUser;
                }).orElseThrow(UserNotFoundException::new);
    }
}
