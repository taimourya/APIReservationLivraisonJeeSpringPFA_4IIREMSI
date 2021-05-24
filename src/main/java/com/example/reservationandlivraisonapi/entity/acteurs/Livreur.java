package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
@DiscriminatorValue("LIVR")
public class Livreur extends ParticulierInfo {

	private Date dateContrat;
	private String TypeVehicule;
	@OneToMany(mappedBy = "livreur", fetch = FetchType.EAGER)
	Collection<Livraison> livraisons = new ArrayList<>();
	/*public Livraison m_Livraison;*/

	public Livreur(Integer user_id, String username, String password, String firstname, String lastname, String CIN, String ville, String adresse, String email, String phone, Date dateNaissance, Date dateContrat, String typeVehicule) {
		super(user_id, username, password, firstname, lastname, CIN, ville, adresse, email, phone, dateNaissance);
		this.dateContrat = dateContrat;
		TypeVehicule = typeVehicule;
	}
}