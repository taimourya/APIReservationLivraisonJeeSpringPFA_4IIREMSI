package com.example.reservationandlivraisonapi.entity.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("FOOD")
public class Food extends Item {

	@ManyToOne
	private FoodCategory category;

	public Food(Integer id, String name, float price, String image, Restaurant restaurant, FoodCategory category) {
		super(id, name, price, image, restaurant);
		this.category = category;
	}
}