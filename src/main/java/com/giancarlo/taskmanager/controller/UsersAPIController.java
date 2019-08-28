package com.giancarlo.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import com.giancarlo.taskmanager.model.User;
import com.giancarlo.taskmanager.repository.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsersAPIController
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersAPIController {
    @Autowired
    private Users usersRepository;

    @GetMapping
    ResponseEntity<List<User>> index() {
        return new ResponseEntity<List<User>>(
            usersRepository.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> show(@PathVariable Long id) {
        return new ResponseEntity<Optional<User>>(usersRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<User> create(@RequestBody User user) {
        usersRepository.save(user);
        return new ResponseEntity<>(
            user,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return new ResponseEntity<String>("Usuário deletado!", HttpStatus.OK);
    } 
}