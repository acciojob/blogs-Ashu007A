package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String username, String password){

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName("test");
        newUser.setLastName("test");
        return userRepository.save(newUser);

    }

    public void deleteUser(int userId){

        userRepository.deleteById(userId);

    }

    public User updateUser(Integer id, String password){

        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(password);
            return userRepository.save(user);
        }
        return null;

    }
}
