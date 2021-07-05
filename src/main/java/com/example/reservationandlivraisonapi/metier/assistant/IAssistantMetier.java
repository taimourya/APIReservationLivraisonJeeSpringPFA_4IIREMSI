package com.example.reservationandlivraisonapi.metier.assistant;

import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;

public interface IAssistantMetier {

	Reclamation consulterReclamationEnCours(int assistant_id) throws Exception;
	Reclamation checkReclamation(int assistant_id) throws Exception;
	Reclamation accepterReclamation(int assistant_id, int reclamation_id) throws Exception;
	Reclamation terminerReclamation(int assistant_id) throws Exception;
	void transferToAssistant(int assistant_id) throws Exception;
	void transferToAssistantExpert(int assistant_id) throws Exception;

}