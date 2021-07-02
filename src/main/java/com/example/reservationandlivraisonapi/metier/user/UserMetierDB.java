package com.example.reservationandlivraisonapi.metier.user;

import com.example.reservationandlivraisonapi.dao.acteurs.EntrepriseInfoRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.ParticulierInfoRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.RestaurantRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.UserRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.ReclamationRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.EntrepriseInfo;
import com.example.reservationandlivraisonapi.entity.acteurs.ParticulierInfo;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class UserMetierDB implements IUserMetier{

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public Conversation checkConversation(int user_id) {
        return null;
    }

    @Override
    public User consulterUser(int user_id) throws Exception{
        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new Exception("utilisateur introuvable");
    }

    @Override
    public Reclamation reclamer(int user_id, String message) throws Exception {
        User user = consulterUser(user_id);
        Reclamation reclamation = new Reclamation(null, message, 0, new Date(), user, null, null);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void replyConversation(int user_id, String message) {

    }
}
