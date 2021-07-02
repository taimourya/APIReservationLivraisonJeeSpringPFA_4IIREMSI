package com.example.reservationandlivraisonapi.entity.commande;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Livreur;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("LIVR")
@Data
@NoArgsConstructor
public class Livraison extends Commande {

	private Date livredDate;
	private int stat; // 0 en cours 1 collect 2 en route 3 livrer
	private float longitude;
	private float latitude;

	@ManyToOne
	private Livreur livreur;

	public Livraison(Integer id, Date date, Client client, Collection<CommandeBuyable> buyables, Date livredDate,
					 int stat, float longitude, float latitude, Livreur livreur) {
		super(id, date, client, buyables);
		this.livredDate = livredDate;
		this.stat = stat;
		this.longitude = longitude;
		this.latitude = latitude;
		this.livreur = livreur;
	}
}