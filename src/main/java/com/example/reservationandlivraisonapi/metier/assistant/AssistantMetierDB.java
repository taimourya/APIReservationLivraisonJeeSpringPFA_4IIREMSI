package com.example.reservationandlivraisonapi.metier.assistant;

import com.example.reservationandlivraisonapi.dao.reclamation.ConversationRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.MessageRepository;
import com.example.reservationandlivraisonapi.dao.reclamation.ReclamationRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Message;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AssistantMetierDB implements IAssistantMetier{

    @Autowired
    IUserMetier userMetier;

    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;


    @Override
    public Reclamation consulterReclamationEnCours(int assistant_id) throws Exception {
        Assistant assistant = (Assistant) userMetier.consulterUser(assistant_id);
        Reclamation reclamation = reclamationRepository.findCurrentReclamation(assistant);
        if(reclamation != null)
            return reclamation;
        throw new Exception("reclamation introuvable");
    }

    @Override
    public Reclamation checkReclamation(int assistant_id) throws Exception {
        Assistant assistant = (Assistant) userMetier.consulterUser(assistant_id);
        if(reclamationRepository.findCurrentReclamation(assistant) != null)
            throw new Exception("vous avez deja une reclamation en cours de traitment");

        Reclamation reclamation = reclamationRepository.findReclamationNonPris();
        if(reclamation == null)
            throw new Exception("rien trouver");
        if(reclamation.getStat() != 0)
            throw new Exception("Nécessite un assistant expert");
        return reclamation;
    }

    @Override
    public Reclamation accepterReclamation(int assistant_id, int reclamation_id) throws Exception {
        Assistant assistant = (Assistant) userMetier.consulterUser(assistant_id);
        Optional<Reclamation> opt = reclamationRepository.findById(reclamation_id);
        if(!opt.isPresent())
            throw new Exception("reclamation introuvable");
        Reclamation reclamation = opt.get();
        if(assistant instanceof Assistant && reclamation.getStat() == -1)
            throw new Exception("reclamation destinnée a un assistant expert");
        reclamation.setAssistant(assistant);
        reclamation.setStat(1);
        Conversation conversation = new Conversation(null, reclamation.getUser(), assistant, null, reclamation);
        conversationRepository.save(conversation);
        Message message = new Message(null, reclamation.getMessage(), new Date(), conversation, reclamation.getUser());
        messageRepository.save(message);
        reclamation.setConversation(conversation);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation terminerReclamation(int assistant_id) throws Exception {
        Reclamation reclamation = consulterReclamationEnCours(assistant_id);
        reclamation.setStat(2);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void transferToAssistant(int assistant_id) throws Exception {
        Reclamation reclamation = consulterReclamationEnCours(assistant_id);
        reclamation.setAssistant(null);
        reclamation.setStat(0);
        reclamationRepository.save(reclamation);
    }

    @Override
    public void transferToAssistantExpert(int assistant_id) throws Exception {
        Reclamation reclamation = consulterReclamationEnCours(assistant_id);
        reclamation.setAssistant(null);
        reclamation.setStat(-1);
        reclamationRepository.save(reclamation);
    }
}
