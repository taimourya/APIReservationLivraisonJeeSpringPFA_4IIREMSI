package com.example.reservationandlivraisonapi.entity.acteurs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("PART")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PARTICULiER", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class ParticulierInfo extends User {

	private String firstname;
	private String lastname;
	private String CIN;
	private String ville;
	private String adresse;
	private String email;
	private String phone;
	private Date dateNaissance;

	public ParticulierInfo(Integer user_id, String username, String password, String firstname, String lastname, String CIN,
						   String ville, String adresse, String email, String phone, Date dateNaissance) {
		super(user_id, username, password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.CIN = CIN;
		this.ville = ville;
		this.adresse = adresse;
		this.email = email;
		this.phone = phone;
		this.dateNaissance = dateNaissance;
	}
}