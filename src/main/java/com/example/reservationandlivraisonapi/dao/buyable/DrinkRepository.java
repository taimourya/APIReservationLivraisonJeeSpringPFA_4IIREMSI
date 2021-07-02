package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Drink;
import com.example.reservationandlivraisonapi.entity.buyable.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    Collection<Drink> findByNameContainsAndRestaurant(String mc, Restaurant restaurant);

}
