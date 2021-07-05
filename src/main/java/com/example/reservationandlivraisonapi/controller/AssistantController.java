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

    @GetMapping("/reclamation/check")
    public Reclamation checkReclamation(int assistant_id) throws Exception {
        return assistantMetier.checkReclamation(assistant_id);
    }
    @GetMapping("/reclamation/accept")
    public Reclamation accepterReclamation(int assistant_id, int reclamation_id) throws Exception {
        return assistantMetier.accepterReclamation(assistant_id, reclamation_id);
    }

    @GetMapping("/reclamation/resolved")
    public void resolveConversation(int assistant_id) throws Exception {
        assistantMetier.terminerReclamation(assistant_id);
    }

    @GetMapping("/reclamation/enCours")
    public Reclamation reclamationEnCours(int assistant_id) throws Exception {
        return assistantMetier.consulterReclamationEnCours(assistant_id);
    }

    @GetMapping("/reclamation/transfer")
    public void transferReclamatin(int assistant_id) throws Exception {
        assistantMetier.transferToAssistant(assistant_id);
    }

    @GetMapping("/reclamation/transfer/expert")
    public void transferReclamatinExpert(int assistant_id) throws Exception {
        assistantMetier.transferToAssistantExpert(assistant_id);
    }

}
