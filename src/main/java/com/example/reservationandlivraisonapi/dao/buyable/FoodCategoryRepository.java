package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.DrinkCategory;
import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, String> {

    Collection<FoodCategory> findByRestaurant(Restaurant restaurant);
}
