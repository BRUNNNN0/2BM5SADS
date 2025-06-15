package com.example.trabalho.controller;


import com.example.trabalho.model.Users;
import com.example.trabalho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/getCurrent")
    public ResponseEntity<UserDetails> getUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable int id) {
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable int id) {
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Users> updateSelf(@AuthenticationPrincipal UserDetails user, @RequestBody Users updatedUser) {
        try{
            return ResponseEntity.ok(userService.updateUser(user.getUsername(),updatedUser));
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users updatedUser) {
        try{
            return ResponseEntity.ok(userService.updateUserById(id,updatedUser));
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}