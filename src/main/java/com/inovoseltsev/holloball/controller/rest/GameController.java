package com.inovoseltsev.holloball.controller.rest;

import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.service.AppUserService;
import com.inovoseltsev.holloball.transfer.GameUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GameController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/usersSave")
    public GameUserDto getUsersSave(HttpServletRequest req) {
        String login = req.getParameter("login");
        if (login != null) {
            AppUser user = userService.findByLogin(login);
            return new GameUserDto(user.getLevel(), user.getSkins(),
                    user.getCoins());
        }
        return null;
    }

    @PostMapping("/updateUsersSave")
    public ResponseEntity<Object> updateUser(@RequestBody GameUserDto gameUserDto) {
        return ResponseEntity.ok().build();
    }
}