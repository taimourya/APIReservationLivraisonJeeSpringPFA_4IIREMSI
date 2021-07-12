package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.example.reservationandlivraisonapi.metier.assistant.IAssistantMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class AssistantController {

    @Autowired
    IAssistantMetier assistantMetier;

    @GetMapping("/assistant/reclamation/check")
    public Reclamation checkReclamation() throws Exception {
        return assistantMetier.checkReclamation();
    }
    @GetMapping("/assistant/reclamation/accept")
    public Reclamation accepterReclamation(int reclamation_id) throws Exception {
        return assistantMetier.accepterReclamation(reclamation_id);
    }

    @GetMapping("/assistant/reclamation/resolved")
    public void resolveConversation() throws Exception {
        assistantMetier.terminerReclamation();
    }

    @GetMapping("/assistant/reclamation/enCours")
    public Reclamation reclamationEnCours() throws Exception {
        return assistantMetier.consulterReclamationEnCours();
    }

    @GetMapping("/assistant/reclamation/transfer")
    public void transferReclamatin() throws Exception {
        assistantMetier.transferToAssistant();
    }

    @GetMapping("/assistant/reclamation/transfer/expert")
    public void transferReclamatinExpert() throws Exception {
        assistantMetier.transferToAssistantExpert();
    }

}
