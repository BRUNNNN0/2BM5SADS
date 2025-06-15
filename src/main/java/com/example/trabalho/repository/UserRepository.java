package com.example.trabalho.repository;


import com.example.trabalho.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByLogin(String login);
}