package com.example.reservationandlivraisonapi.metier.client;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entity.note.Note;
import com.example.reservationandlivraisonapi.entity.paraignage.Paraignage;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IClientMetier {

	public void Inscription(String username, String password, String passwordConfirm, String email, String adresse, String telephone, String firstname, String lastname, String CIN, Date dateNaissance);

	public Collection<Buyable> consulterMenu(String mc);
	public Collection<Buyable> consulterMenuRestaurant(int restaurant_id, String mc) throws Exception;
	public Collection<Restaurant> consulterRestaurantProche(float longitude, float latitude);
	public Livraison commanderLivraison(int client_id, float longitude, float latitude) throws Exception;
	public Reservation commanderReservation(int client_id, Date dateReservation) throws Exception;
	public Collection<Livraison> consulterLivraisonEnCours(int client_id) throws Exception;
	public Collection<Reservation> consulterReservationEnCours(int client_id) throws Exception;
	public Note noterLivreur(int client_id, int livreur_id, String message, int etoile) throws Exception;
	public Note noterRestaurant(int client_id, int restaurant_id, String message, int etoile) throws Exception;
	public String getCodePromo(int client_id) throws Exception;
	public Paraignage useCodePromo(int client_id, String codePromo) throws Exception;
	public Collection<Commande> consulterHistorique(int client_id) throws Exception;
	public PanierWithInfo consulterPanier(int client_id) throws Exception;
	public void ajouterAuPanier(int buyable_id, int qtn, int client_id) throws Exception;
	public void removeQtnPanier(int buyable_id, int qtn, int client_id) throws Exception;
	public void supprimerDuPanier(int buyable_id, int client_id) throws Exception;
	public void viderPanier(int client_id) throws Exception;
	public float totalPanier(int client_id) throws Exception;

}