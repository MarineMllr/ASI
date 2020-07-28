package com.sp.service;


import com.sp.dto.Login;
import com.sp.model.Authentication;
import com.sp.repository.AuthenticationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    public String login(Login login) {
        String url = "http://localhost" + ":" + "8083" + "/user/login";
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("surname", login.getSurname());
        jsonObject.put("password", login.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> request =
                new HttpEntity<>(jsonObject.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            if(response.getStatusCode() == HttpStatus.OK){
                int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 10;
                Random random = new Random();

                String token = random.ints(leftLimit, rightLimit + 1)
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                Authentication authentication = new Authentication(token);
                authenticationRepository.save(authentication);
                if(authenticationRepository.findByToken(token) != null){
                    return token;
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
            return null;
        }
        return null;
    }

    public boolean verifyToken(String token) {
        return authenticationRepository.findByToken(token) != null;
    }
}
