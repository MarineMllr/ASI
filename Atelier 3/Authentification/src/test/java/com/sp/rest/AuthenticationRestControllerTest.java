package com.sp.rest;

import com.sp.model.Authentication;
import com.sp.rest.AuthenticationRestController;
import com.sp.service.AuthenticationService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationRestControllerTest {

    @InjectMocks
    AuthenticationRestController authenticationRestController;

    @Mock
    AuthenticationService authenticationService;

    @Test
    public void testVerifyTokenOk(){
        MockitoAnnotations.initMocks(this);
        when(authenticationService.verifyToken(any())).thenReturn(true);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), authenticationRestController.verifyToken(any()));
    }

    @Test
    public void testVerifyTokenNotFound(){
        MockitoAnnotations.initMocks(this);
        when(authenticationService.verifyToken(any())).thenReturn(false);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), authenticationRestController.verifyToken(any()));
    }
}
