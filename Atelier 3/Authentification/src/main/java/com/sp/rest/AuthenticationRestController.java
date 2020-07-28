package com.sp.rest;

import com.sp.dto.Login;
import com.sp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationRestController {

    @Autowired
    AuthenticationService authenticationService;

    // Vérification d'un token envoyé
    @RequestMapping(value = "/authentication/verify", method = RequestMethod.GET)
    public ResponseEntity<?> verifyToken(@RequestParam String token) {
        if(authenticationService.verifyToken(token)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Récupération d'un token
    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Login login) {
        String token = authenticationService.login(login);
        if(token != null){
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(token, HttpStatus.NOT_FOUND);

    }
}
