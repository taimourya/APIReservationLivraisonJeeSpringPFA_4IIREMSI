package com.example.reservationandlivraisonapi.metier.user;

import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import com.example.reservationandlivraisonapi.entity.reclamation.Message;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;

import java.util.Collection;

public interface IUserMetier {

	User consulterUser(int user_id) throws Exception;
	User consulterUser(String username) throws Exception;
	User consulterAuthentificateUser() throws Exception;
	Reclamation reclamer(String message) throws Exception;
	User login(String username, String passwd, String source) throws Exception;
	boolean checkAccess(String source) throws Exception;
	Conversation checkConversation() throws Exception;
	Message sendMessage(String message, int conversation_id) throws Exception;
	Collection<Message> consulterMessagesConversation(int conversation_id) throws Exception;
}