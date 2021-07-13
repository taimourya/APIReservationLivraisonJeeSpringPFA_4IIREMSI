package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("ENTR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ENTREPRISE", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class EntrepriseInfo extends User {

	private String ville;
	private String adresse;
	private float latitude;
	private float longitude;
	private String name;
	private String phone;

	public EntrepriseInfo(Integer user_id, String username, String password, String ville, String adresse,
						  String name, String phone) {
		super(user_id, username, password, null);
		this.ville = ville;
		this.adresse = adresse;
		this.latitude = 0;
		this.longitude = 0;
		this.name = name;
		this.phone = phone;
	}

	public EntrepriseInfo(Integer user_id, String username, String password, Collection<Reclamation> reclamations, String ville, String adresse, float latitude, float longitude, String name, String phone) {
		super(user_id, username, password, reclamations);
		this.ville = ville;
		this.adresse = adresse;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.phone = phone;
	}
}