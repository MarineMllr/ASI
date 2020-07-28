package com.sp.rest;

import com.sp.dto.Login;
import com.sp.model.User;
import com.sp.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRestControllerTest {

    @InjectMocks
    UserRestController userRestController;

    @Mock
    UserService userService;

    @Test
    public void testListAllUsersOk(){
        MockitoAnnotations.initMocks(this);
        List<User> users = new ArrayList<User>();
        User user = new User();
        users.add(user);
        when(userService.findAllUsers()).thenReturn(users);
        assertEquals(new ResponseEntity<>(users, HttpStatus.OK), userRestController.listAllUsers());
    }

    @Test
    public void testListAllUsersEmpty(){
        MockitoAnnotations.initMocks(this);
        List<User> users = new ArrayList<User>();
        when(userService.findAllUsers()).thenReturn(users);
        assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT), userRestController.listAllUsers());
    }

    @Test
    public void testGetUserOk(){
        MockitoAnnotations.initMocks(this);
        User user = new User("oui","non","non");
        when(userService.findById(anyInt())).thenReturn(user);
        assertEquals(new ResponseEntity<>(user, HttpStatus.OK), userRestController.getUser(anyInt()));
    }

    @Test
    public void testGetUserNotFound(){
        MockitoAnnotations.initMocks(this);
        when(userService.findById(anyInt())).thenReturn(null);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), userRestController.getUser(anyInt()));
    }

    @Test
    public void testCreateUserOk(){
        MockitoAnnotations.initMocks(this);
        when(userService.isUserExist(any())).thenReturn(false);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), userRestController.createUser(any()));
    }

    @Test
    public void testCreateUserConflict(){
        MockitoAnnotations.initMocks(this);
        when(userService.isUserExist(any())).thenReturn(true);
        assertEquals(new ResponseEntity<>(HttpStatus.CONFLICT), userRestController.createUser(any()));
    }

    @Test
    public void testAuthenticateUserOk(){
        MockitoAnnotations.initMocks(this);
        User user = new User("oui","non","non");
        Login login = new Login();
        when(userService.authenticate(any(), any())).thenReturn(user);
        assertEquals(new ResponseEntity<>(user, HttpStatus.OK), userRestController.authenticateUser(login));
    }

    @Test
    public void testAuthenticateUserConflict(){
        MockitoAnnotations.initMocks(this);
        Login login = new Login();
        when(userService.authenticate(any(), any())).thenReturn(null);
        when(userService.findBySurname(any())).thenReturn(true);
        assertEquals(new ResponseEntity<>("Mauvais mot de passe", HttpStatus.CONFLICT), userRestController.authenticateUser(login));
    }

    @Test
    public void testAuthenticateUserNotFound(){
        MockitoAnnotations.initMocks(this);
        Login login = new Login();
        when(userService.authenticate(any(), any())).thenReturn(null);
        when(userService.findBySurname(any())).thenReturn(false);
        assertEquals(new ResponseEntity<>("L'utilisateur n'existe pas", HttpStatus.NOT_FOUND), userRestController.authenticateUser(login));
    }
}
