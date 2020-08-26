package com.sp.controllers;

import com.sp.dto.Login;
import com.sp.dto.MoneyExchangeDTO;
import com.sp.dto.UserDTO;
import com.sp.model.User;
import com.sp.services.UserService;
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

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestParam (name = "name") String name, @RequestParam (name = "surname") String surname, @RequestParam (name = "password") String password) {
        UserDTO user=new UserDTO(name, surname, password);
        if (userService.findBySurname(user.getSurname())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.addUser(user);
        return new ResponseEntity<String>(HttpStatus.OK);
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

    @RequestMapping(value = "/user/money/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMoney(@RequestBody MoneyExchangeDTO echange) {
        User user = userService.findById(echange.getIdUser());
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.addMoney(user, echange.getMoney());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/money/remove", method = RequestMethod.POST)
    public ResponseEntity<?> removeMoney(@RequestParam (name = "idUser") int idUser, @RequestParam (name = "money") int money) {
        User user = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.removeMoney(user, money);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
