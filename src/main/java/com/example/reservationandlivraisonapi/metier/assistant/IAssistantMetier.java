package com.example.reservationandlivraisonapi.metier.assistant;

import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;

public interface IAssistantMetier {

	Reclamation consulterReclamationEnCours() throws Exception;
	Reclamation checkReclamation() throws Exception;
	Reclamation accepterReclamation(int reclamation_id) throws Exception;
	Reclamation terminerReclamation() throws Exception;
	void transferToAssistant() throws Exception;
	void transferToAssistantExpert() throws Exception;

}