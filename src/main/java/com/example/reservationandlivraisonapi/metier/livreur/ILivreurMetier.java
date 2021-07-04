package com.example.reservationandlivraisonapi.metier.livreur;

import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;

import java.util.Collection;


public interface ILivreurMetier {

	void accepterLivraison(int livreur_id, int livraison_id) throws Exception;
	Livraison checkNewLivraison(int livreur_id) throws Exception;
	String nextStepLivraison(int livreur_id) throws Exception;
	Livraison consulterLivraisonEnCours(int livreur_id) throws Exception;
	Collection<CommandeBuyable> consulterItemsLivraisonEnCours(int livreur_id) throws Exception;
	Collection<Livraison> consulterHistoriqueLivraison(int livreur_id) throws Exception;
}