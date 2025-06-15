package com.example.trabalho.controller;

import com.example.trabalho.infra.security.TokenService;
import com.example.trabalho.model.Pessoa;
import com.example.trabalho.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Pessoa user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Pessoa) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Pessoa user) {
        if (this.pessoaRepository.findByLogin(user.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe !");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        Pessoa newUser = new Pessoa(user.getLogin(), encryptedPassword, user.getRole());
        this.pessoaRepository.save(newUser);

        return ResponseEntity.ok().body("Usuário registrado !");
    }
}