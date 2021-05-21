package com.example.reservationandlivraisonapi.entity.acteurs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Data
@NoArgsConstructor
@DiscriminatorValue("REST")
public class Restaurant extends EntrepriseInfo {

	/*public Buyable m_Buyable;
	public Reservation m_Reservation;*/


}