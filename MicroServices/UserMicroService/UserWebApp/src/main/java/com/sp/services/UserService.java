package com.sp.services;

import com.sp.dto.UserDTO;
import com.sp.model.User;
import com.sp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserDTO userDTO) {
        User u = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getPassword());
        userRepository.save(u);
    }

    public boolean isUserExist(User user) {
        if(userRepository.findById(user.getId()) != null){
            return true;
        };
        return false;
    }

    public List<User> findAllUsers (){
        Iterable<User> users = userRepository.findAll();
        List<User> result =new ArrayList<>();
        users.forEach(result::add);
        return result;
    }

    public User findById(int id) {
        return userRepository.findById(id);

    }

    public User authenticate(String surname, String password) {
        return userRepository.findBySurnameAndPassword(surname, password);
    }

    public boolean findBySurname(String surname) {
        return !userRepository.findBySurname(surname).isEmpty();
    }

    public void addMoney(User user, int money) {
        user.setMoney(user.getMoney() + money);
        userRepository.save(user);
    }

    public void removeMoney(User user, int money) {
        user.setMoney(user.getMoney() - money);
        userRepository.save(user);
    }
}
