package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import com.lakhan.restprojects.hackerrankclone.models.User;
import com.lakhan.restprojects.hackerrankclone.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value="/register")
    public void register(@Valid @RequestBody User user) {
       user.setCreatedTime(LocalDateTime.now());
       user.setRegistrationStatus(RegistrationStatus.NOT_YET_ACTIVATED);
       usersService.register(user);
    }

    @PostMapping("/login")
    public void login(User user) {
        //After successful login
        user.setLastLoggedInTime(LocalDateTime.now());
    }
    @GetMapping("/verify-registration/{verificationLink}")
    public void verifyRegistration( @PathVariable("verificationLink") String verificationLink) {
        System.out.println(verificationLink);
    }
}
