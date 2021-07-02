package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.DrinkCategory;
import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DrinkCategoryRepository extends JpaRepository<DrinkCategory, String> {

    Collection<DrinkCategory> findByRestaurant(Restaurant restaurant);

}
