package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.Form.LoginForm;
import com.example.reservationandlivraisonapi.Form.MessageForm;
import com.example.reservationandlivraisonapi.Form.ReclamationForm;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Message;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin()
public class UserController {

    @Autowired
    IUserMetier userMetier;

    @GetMapping("/user")
    public User getUser(@RequestParam() Integer id) throws Exception {
        return userMetier.consulterUser(id);
    }

    @GetMapping("/user/profile")
    public User getProfile() throws Exception {
        return userMetier.consulterAuthentificateUser();
    }

    @PostMapping("/user/create/reclamation")
    public Reclamation createReclamation(@RequestBody ReclamationForm reclamationForm) throws Exception {
        return userMetier.reclamer(reclamationForm.getMessage());
    }

    @PostMapping("/user/login")
    public User login(@RequestBody LoginForm loginForm) throws Exception {
        return userMetier.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getSource());
    }
    @GetMapping("/user/conversation/check")
    public Conversation checkConversation() throws Exception {
        return userMetier.checkConversation();
    }
    @GetMapping("user/conversation/messages")
    public Collection<Message> getMessages(@RequestParam() int conversation_id) throws Exception {
        return userMetier.consulterMessagesConversation(conversation_id);
    }
    @PostMapping("/user/conversation/message/send")
    public Message createMessage(@RequestBody MessageForm messageForm) throws Exception {
        return userMetier.sendMessage(messageForm.getMessage(), messageForm.getConversation_id());
    }
}
