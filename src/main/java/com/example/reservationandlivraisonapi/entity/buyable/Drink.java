package com.example.reservationandlivraisonapi.entity.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("DRNK")
public class Drink extends Item {

	@ManyToOne(fetch = FetchType.LAZY)
	private DrinkCategory category;

	public Drink(Integer id, String name, float price, String image, Restaurant restaurant, DrinkCategory category) {
		super(id, name, price, image, restaurant);
		this.category = category;
	}
}