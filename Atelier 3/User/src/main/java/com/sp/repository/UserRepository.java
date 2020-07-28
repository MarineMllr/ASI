package com.sp.repository;

import com.sp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findBySurname(String surname);

    User findById(int id);

    User findBySurnameAndPassword(String surname, String password);
}
