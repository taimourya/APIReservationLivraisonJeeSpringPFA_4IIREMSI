package com.example.reservationandlivraisonapi.entity.buyable;


import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CATEGORY", discriminatorType = DiscriminatorType.STRING, length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Category  implements Serializable {


	@Id
	private String name;

	@ManyToOne
	private Restaurant restaurant;


}