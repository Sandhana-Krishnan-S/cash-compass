package com.sandu.cashcompass.controller;

import com.sandu.cashcompass.model.User;
import com.sandu.cashcompass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User u) {
        return service.register(u);
    }
    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User u) {
        return service.login(u);
    }
    @DeleteMapping("/deleteAcc")
    public ResponseEntity<User> deleteAcc(@RequestBody User user) {
        return service.deleteAcc(user);
    }
}
