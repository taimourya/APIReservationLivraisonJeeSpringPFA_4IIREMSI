package com.example.reservationandlivraisonapi.entity.commande;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("RESV")
@Data
@NoArgsConstructor
public class Reservation extends Commande {

	@ManyToOne
	private Restaurant restaurant;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateReservation;

	public Reservation(Integer id, Date date, Client client, Collection<CommandeBuyable> buyables, Restaurant restaurant,
					   Date dateReservation) {
		super(id, date, client, buyables);
		this.restaurant = restaurant;
		this.dateReservation = dateReservation;
	}
}