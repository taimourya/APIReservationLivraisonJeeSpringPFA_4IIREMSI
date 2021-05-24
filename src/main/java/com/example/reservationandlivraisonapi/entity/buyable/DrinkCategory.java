package com.example.reservationandlivraisonapi.entity.buyable;


import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
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


	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Drink> items = new ArrayList<>();

	public DrinkCategory(Integer id, String name, Restaurant restaurant) {
		super(id, name, restaurant);
	}
}