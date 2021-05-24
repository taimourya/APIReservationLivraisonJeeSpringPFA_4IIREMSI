package com.example.reservationandlivraisonapi.entity.buyable;


import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("DRNK")
public class DrinkCategory extends Category {


	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Drink> items;

	public DrinkCategory(Integer id, String name, Restaurant restaurant) {
		super(id, name, restaurant);
		this.items = items;
	}
}