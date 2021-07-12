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
	public Livraison commanderLivraison(float longitude, float latitude) throws Exception;
	public Reservation commanderReservation(Date dateReservation) throws Exception;
	public Collection<Livraison> consulterLivraisonEnCours() throws Exception;
	public Collection<Reservation> consulterReservationEnCours() throws Exception;
	public Note noterLivreur(int livreur_id, String message, int etoile) throws Exception;
	public Note noterRestaurant(int restaurant_id, String message, int etoile) throws Exception;
	public String getCodePromo() throws Exception;
	public Paraignage useCodePromo(String codePromo) throws Exception;
	public Collection<Commande> consulterHistorique() throws Exception;
	public PanierWithInfo consulterPanier() throws Exception;
	public void ajouterAuPanier(int buyable_id, int qtn) throws Exception;
	public void removeQtnPanier(int buyable_id, int qtn) throws Exception;
	public void supprimerDuPanier(int buyable_id) throws Exception;
	public void viderPanier() throws Exception;
	public float totalPanier() throws Exception;

}