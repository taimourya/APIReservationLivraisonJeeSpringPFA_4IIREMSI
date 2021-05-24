package com.example.reservationandlivraisonapi.entity.commande;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("RESV")
@Data
@NoArgsConstructor
public class Reservation extends Commande {

	@ManyToOne
	private Restaurant restaurant;
	private Date dateReservation;

	public Reservation(Integer id, Date date, Client client, Collection<CommandeBuyable> buyables, Restaurant restaurant,
					   Date dateReservation) {
		super(id, date, client, buyables);
		this.restaurant = restaurant;
		this.dateReservation = dateReservation;
	}
}