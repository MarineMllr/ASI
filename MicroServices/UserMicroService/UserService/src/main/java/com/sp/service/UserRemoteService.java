package com.sp.service;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sp.model.User;

@Service
public class UserRemoteService {
    private static final String SERVICE_URL = "http://localhost:8083/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<User> findById(Integer userID) {
        try {
            User user = restTemplate.getForObject(SERVICE_URL + userID, User.class);
            return Optional.of(user);
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }

    public void update(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user);
        restTemplate.postForObject(SERVICE_URL, entity, User.class);
    }
}