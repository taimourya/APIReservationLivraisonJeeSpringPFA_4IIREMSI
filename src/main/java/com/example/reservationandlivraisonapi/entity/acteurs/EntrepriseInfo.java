package com.example.reservationandlivraisonapi.entity.acteurs;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("ENTR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ENTREPRISE", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class EntrepriseInfo extends User {

	private String adresse;
	private float latitude;
	private float longitude;
	private String name;
	private String phone;

	public EntrepriseInfo(Integer user_id, String username, String password, String adresse, float latitude, float longitude, String name, String phone) {
		super(user_id, username, password);
		this.adresse = adresse;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.phone = phone;
	}
}