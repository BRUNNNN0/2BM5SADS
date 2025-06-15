package com.example.trabalho.service;

import com.example.trabalho.model.Pessoa;
import com.example.trabalho.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa createUser(Pessoa user) {
        return pessoaRepository.save(user);
    }

    public Pessoa getUserById(long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Pessoa updateUser(String username, Pessoa user) {
        UserDetails userDetails = pessoaRepository.findByLogin(username);

        if (userDetails == null) {
            throw new RuntimeException("Usuário não encontrado com o login: " + username);
        }

        // Faz o cast para Pessoa (assumindo que UserDetails é implementado por Pessoa)
        Pessoa oldUser = (Pessoa) userDetails;

        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());

        return pessoaRepository.save(oldUser);
    }

    public Pessoa updateUserById(long id, Pessoa user) {
        Pessoa oldUser = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());
        return pessoaRepository.save(oldUser);
    }

    public Pessoa deleteUser(long id) {
        Pessoa oldUser = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        pessoaRepository.delete(oldUser);
        return oldUser;
    }
}
