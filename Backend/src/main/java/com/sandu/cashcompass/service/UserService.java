package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.User;
import com.sandu.cashcompass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity register(User newUser) {
        if(repository.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity<>("You have an existing account",HttpStatus.SEE_OTHER);
        }
        try {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
            if(newUser.getEmail().matches(emailRegex) && newUser.getPassword().matches(passwordRegex) && newUser.getUsername().length() < 3) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String hashedPassword  = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(hashedPassword);
            newUser.initializer();
            repository.save(newUser);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newUser ,HttpStatus.CREATED);
    }

    public ResponseEntity login(User user) {
        User exist = repository.findByEmail(user.getEmail());
        if (exist == null) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (passwordEncoder.matches(user.getPassword(), exist.getPassword())) {
            return new ResponseEntity<>(exist, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Email or Password is Invalid" ,HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<User> deleteAcc(User user) {
        User exist = repository.findByEmail(user.getEmail());
        if(exist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (passwordEncoder.matches(user.getPassword(), exist.getPassword())) {
            repository.deleteById(exist.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
