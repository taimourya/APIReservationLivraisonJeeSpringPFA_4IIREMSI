package com.example.reservationandlivraisonapi.metier.livreur;

import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;

import java.util.Collection;


public interface ILivreurMetier {

	void accepterLivraison(int livraison_id) throws Exception;
	Livraison checkNewLivraison() throws Exception;
	String nextStepLivraison() throws Exception;
	Livraison consulterLivraisonEnCours() throws Exception;
	Collection<CommandeBuyable> consulterItemsLivraisonEnCours() throws Exception;
	Collection<Livraison> consulterHistoriqueLivraison() throws Exception;
}