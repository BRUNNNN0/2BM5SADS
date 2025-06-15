package com.example.trabalho.repository;

import com.example.trabalho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    UserDetails findByLogin(String login);
}