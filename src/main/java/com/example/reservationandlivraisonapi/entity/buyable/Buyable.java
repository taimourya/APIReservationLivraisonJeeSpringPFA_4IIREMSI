package com.example.reservationandlivraisonapi.entity.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.EntrepriseInfo;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_BUYABLE", discriminatorType = DiscriminatorType.STRING, length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Buyable implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private float price;
	private String image;

	@ManyToOne
	private Restaurant restaurant;

	@OneToMany(mappedBy = "buyable")
	@JsonIgnore
	private Collection<CommandeBuyable> commandeBuyables = new ArrayList<>();;



}