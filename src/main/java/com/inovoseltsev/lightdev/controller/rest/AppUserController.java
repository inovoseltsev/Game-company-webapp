package com.inovoseltsev.lightdev.controller.rest;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.service.AppUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appUsers")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserService.findAll();
    }

    @GetMapping("{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/login/{login}")
    public AppUser getUserByLogin(@PathVariable String login) {
        return null;
    }

    @GetMapping("/email/{email}")
    public AppUser getUserByEmail(@PathVariable String email) {
        return null;
    }

    @PostMapping("/add")
    public AppUser createUser(@RequestBody AppUser user) {
        return null;
    }

    @PutMapping("{id}")
    public AppUser updateUserById(@PathVariable Long id,
                                  @RequestBody AppUser user) {
        return null;
    }

    @PutMapping("/login/{login}")
    public AppUser updateUserByLogin(@PathVariable String login,
                                     @RequestBody AppUser user) {
        return null;
    }

    @PutMapping("/email/{email}")
    public AppUser updateUserByEmail(@PathVariable String email,
                                     @RequestBody AppUser user) {
        return null;
    }

}
