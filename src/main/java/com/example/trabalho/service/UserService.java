package com.example.trabalho.service;


import com.example.trabalho.model.Users;
import com.example.trabalho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public Users getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Users updateUser(String username, Users user) {
        Users oldUser = userRepository.findByLogin(username);

        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    public Users updateUserById(int id, Users user) {
        Users oldUser = userRepository.findById(id).orElseThrow();

        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    public Users deleteUser(int id) {
        Users oldUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(oldUser);
        return oldUser;
    }
}