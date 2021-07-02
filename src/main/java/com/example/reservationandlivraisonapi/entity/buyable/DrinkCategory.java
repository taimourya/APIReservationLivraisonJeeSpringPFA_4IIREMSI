package com.example.reservationandlivraisonapi.entity.buyable;


import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("DRNK")
public class DrinkCategory extends Category {


	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private Collection<Drink> items = new ArrayList<>();

	public DrinkCategory(String name, Restaurant restaurant) {
		super(name, restaurant);
	}
}