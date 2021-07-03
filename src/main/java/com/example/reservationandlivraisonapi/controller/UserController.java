package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.Form.LoginForm;
import com.example.reservationandlivraisonapi.Form.ReclamationForm;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    IUserMetier userMetier;

    @GetMapping("/user")
    public User getUser(@RequestParam() Integer id) throws Exception {
        return userMetier.consulterUser(id);
    }

    @PostMapping("/create/reclamation")
    public Reclamation createReclamation(@RequestBody ReclamationForm reclamationForm) throws Exception {
        return userMetier.reclamer(reclamationForm.getUser_id(), reclamationForm.getMessage());
    }

    @PostMapping("/user/login")
    public User login(@RequestBody LoginForm loginForm) throws Exception {
        return userMetier.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getSource());
    }
}
