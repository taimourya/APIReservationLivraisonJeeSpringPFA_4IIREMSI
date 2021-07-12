package com.example.reservationandlivraisonapi.metier.user;

import com.example.reservationandlivraisonapi.dao.acteurs.EntrepriseInfoRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.ParticulierInfoRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.RestaurantRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.UserRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.ConversationRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.MessageRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.ReclamationRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.*;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Message;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Component
public class UserMetierDB implements IUserMetier{

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;


    @Override
    public User consulterUser(int user_id) throws Exception{
        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new Exception("utilisateur introuvable");
    }

    @Override
    public User consulterUser(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new Exception("utilisateur introuvable");
        return user;
    }

    @Override
    public User consulterAuthentificateUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return consulterUser(username);
    }

    @Override
    public Reclamation reclamer(String message) throws Exception {
        User user = consulterAuthentificateUser();
        Reclamation reclamation = new Reclamation(null, message, 0, new Date(), user, null, null);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public User login(String username, String passwd, String source) throws Exception {
        User user = userRepository.findByUsernameAndPassword(username, passwd);
        if(user == null)
            throw new Exception("username or password incorrect");
        if(source.equalsIgnoreCase("restaurant") && user instanceof Restaurant)
            return user;
        if(source.equalsIgnoreCase("livreur") && user instanceof Livreur)
            return user;
        if(source.equalsIgnoreCase("assistant") && user instanceof Assistant)
            return user;
        if(source.equalsIgnoreCase("client") && user instanceof Client)
            return user;
        if(source.equalsIgnoreCase("admin") && user instanceof Client)
            return user;
        throw new Exception("Acces refusé");
    }

    @Override
    public Conversation checkConversation() throws Exception {
        User user = consulterAuthentificateUser();
        Conversation conversation = conversationRepository.findCurrentConversation(user);
        if(conversation == null)
            throw new Exception("Aucune conversation trouvé");
        return conversation;
    }

    @Override
    public Message sendMessage(String message, int conversation_id) throws Exception {
        User user = consulterAuthentificateUser();
        Optional<Conversation> opt = conversationRepository.findById(conversation_id);
        if(!opt.isPresent())
            throw new Exception("conversation introuvable");
        Message msg = new Message(null, message, new Date(), opt.get(), user);
        return messageRepository.save(msg);
    }

    @Override
    public Collection<Message> consulterMessagesConversation(int conversation_id) throws Exception {
        Optional<Conversation> opt = conversationRepository.findById(conversation_id);
        if(!opt.isPresent())
            throw new Exception("conversation introuvable");
        return messageRepository.findByConversation(opt.get());
    }
}
