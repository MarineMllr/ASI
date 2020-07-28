package com.sp.rest;

import com.sp.dto.Login;
import com.sp.model.User;
import com.sp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    // Récupérer tous les utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Récupérer un utilisateur avec son id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Créer un utilisateur
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.addUser(user);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody Login login){
        User user = userService.authenticate(login.getSurname(), login.getPassword());
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else if (userService.findBySurname(login.getSurname())) {
            return new ResponseEntity<>("Mauvais mot de passe", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("L'utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }
    }
}
