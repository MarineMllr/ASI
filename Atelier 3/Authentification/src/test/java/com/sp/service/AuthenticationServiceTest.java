package com.sp.service;

import com.sp.dto.Login;
import com.sp.service.AuthenticationService;
import org.json.HTTP;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    //Impossible de mock le restTemplate de la méthode login en l'état, les tests ne sont donc pas valides
    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void loginTestOk(){
        MockitoAnnotations.initMocks(this);
        Login login = new Login("patate", "patate");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("some response body", header, HttpStatus.OK);
        when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), eq(String.class))).thenReturn(responseEntity);
        assertNotNull(authenticationService.login(login));
    }

    @Test
    public void loginTestFail(){
        MockitoAnnotations.initMocks(this);
        Login login = new Login("patate", "patate");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity responseEntity = new ResponseEntity("some response body", header, HttpStatus.NOT_FOUND);
        when(restTemplate.postForEntity(any(), any(), any())).thenReturn(responseEntity);
        assertNull(authenticationService.login(login));
    }
}
