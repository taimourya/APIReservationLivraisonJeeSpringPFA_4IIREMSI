package com.example.reservationandlivraisonapi.metier.user;

import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;

public interface IUserMetier {

	public Conversation checkConversation(int user_id);
	public User consulterUser(int user_id) throws Exception;
	public Reclamation reclamer(int user_id, String message) throws Exception;
	public void replyConversation(int user_id, String message);

	

}