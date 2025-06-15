package com.example.trabalho.controller;


import com.example.trabalho.infra.security.TokenService;
import com.example.trabalho.model.Users;
import com.example.trabalho.repository.UserRepository;
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
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Users user ){
        var usernamePassword=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token=tokenService.generateToken((Users)auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Users user){
        if(this.userRepository.findByLogin(user.getUsername())!=null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        Users newUser=new Users(user.getUsername(),encryptedPassword,user.getRole());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}