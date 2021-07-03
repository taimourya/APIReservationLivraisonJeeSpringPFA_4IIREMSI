package com.example.reservationandlivraisonapi.entity.commande;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeBuyable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int qtn;
	@ManyToOne
	private Commande commande;
	@ManyToOne
	private Buyable buyable;
	@ManyToOne
	private Client client;

	public CommandeBuyable(Integer id, int qtn, Commande commande, Buyable buyable) {
		this.id = id;
		this.qtn = qtn;
		this.commande = commande;
		this.buyable = buyable;
	}
}