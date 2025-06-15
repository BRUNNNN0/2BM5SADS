package com.example.trabalho.controller;


import com.example.trabalho.model.Pessoa;
import com.example.trabalho.service.AuthService;
import com.example.trabalho.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
public class TestController {


    public class UserController {
        @Autowired
        private PessoaService pessoaService;

        @GetMapping
        public ResponseEntity<List<Pessoa>> getUsers() {
            return ResponseEntity.ok(pessoaService.findAll());
        }

        @GetMapping("/getCurrent")
        public ResponseEntity<UserDetails> getUser(@AuthenticationPrincipal UserDetails user) {
            return ResponseEntity.ok(user);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Pessoa> getUser(@PathVariable int id) {
            try{
                return ResponseEntity.ok(pessoaService.getUserById(id));
            }
            catch(Exception e){
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Pessoa> deleteUser(@PathVariable int id) {
            try{
                return ResponseEntity.ok(pessoaService.deleteUser(id));
            }
            catch(Exception e){
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/edit")
        public ResponseEntity<Pessoa> updateSelf(@AuthenticationPrincipal UserDetails user, @RequestBody Pessoa updatedUser) {
            try{
                return ResponseEntity.ok(pessoaService.updateUser(user.getUsername(),updatedUser));
            }
            catch(Exception e){
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Pessoa> updateUser(@PathVariable int id, @RequestBody Pessoa updatedUser) {
            try{
                return ResponseEntity.ok(pessoaService.updateUserById(id,updatedUser));
            }
            catch(Exception e){
                return ResponseEntity.notFound().build();
            }
        }
    }

}
