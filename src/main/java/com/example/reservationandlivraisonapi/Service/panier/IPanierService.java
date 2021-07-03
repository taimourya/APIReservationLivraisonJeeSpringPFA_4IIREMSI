package com.example.reservationandlivraisonapi.Service.panier;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;

import java.util.Date;

public interface IPanierService {

	//id can be session id or id_client on database or ...
	public void add(int buyable_id, int qtn, int id) throws Exception;
	public void clear(int id) throws Exception;
	public void remove(int buyable_id, int qtn, int id) throws Exception;
	public void delete(int buyable_id, int id) throws Exception;
	public Livraison toLivraison(Client client, float longitude, float latitude) throws Exception;
	public Reservation toReservation(Client client, Date dateReservation) throws Exception;
	public PanierWithInfo toPanierWithInfo(int id) throws Exception;
	public float getTotal(int id) throws Exception;

}