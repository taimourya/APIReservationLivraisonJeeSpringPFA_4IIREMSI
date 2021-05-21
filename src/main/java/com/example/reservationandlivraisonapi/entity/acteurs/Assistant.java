package com.example.reservationandlivraisonapi.entity.acteurs;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ASSISTANT", discriminatorType = DiscriminatorType.STRING, length = 4)
@DiscriminatorValue("ASSI")
public class Assistant extends ParticulierInfo {


	private Date dateContrat;
	/*public Reclamation m_Reclamation;
	public Conversation m_Conversation;*/

	public Assistant(Integer user_id, String username, String password, String firstname, String lastname, String CIN, String ville, String adresse, String email, String phone, Date dateNaissance, Date dateContrat) {
		super(user_id, username, password, firstname, lastname, CIN, ville, adresse, email, phone, dateNaissance);
		this.dateContrat = dateContrat;
	}
}