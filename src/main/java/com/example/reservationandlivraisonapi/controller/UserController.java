package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.dao.UserRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public User getUser(@RequestParam() Integer id){
        return userRepository.findById(id).get();
    }
}
