package com.sp.repository;

import com.sp.model.Authentication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<Authentication, Integer> {

    Authentication findByToken(String token);
}
