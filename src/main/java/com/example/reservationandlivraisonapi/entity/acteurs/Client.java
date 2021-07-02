package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.paraignage.Paraignage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("CLIE")
public class Client extends ParticulierInfo {

	private String codePromo;
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	Collection<Commande> commandes = new ArrayList<>();

	@OneToMany(mappedBy = "parraineur")
	@JsonIgnore
	Collection<Paraignage> parraineurs = new ArrayList<>();
	@OneToMany(mappedBy = "parrainer")
	@JsonIgnore
	Collection<Paraignage> parrainees = new ArrayList<>();

	public Client(Integer user_id, String username, String password, String firstname, String lastname, String CIN, String ville, String adresse, String email, String phone, Date dateNaissance, String codePromo) {
		super(user_id, username, password, firstname, lastname, CIN, ville, adresse, email, phone, dateNaissance);
		this.codePromo = codePromo;
	}
}