package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.buyable.Drink;
import com.example.reservationandlivraisonapi.entity.buyable.Food;
import com.example.reservationandlivraisonapi.entity.buyable.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

@Repository
public interface BuyableRepository extends JpaRepository<Buyable, Integer> {

    @Query("SELECT m from Menu m")
    Collection<Menu> findAllMenu();

    @Query("SELECT f from Food f")
    Collection<Food> findAllFood();

    @Query("SELECT d from Drink d")
    Collection<Drink> findAllDrink();

    @Query("SELECT b from Buyable b where b.restaurant.user_id = ?1")
    Collection<Buyable> findBuyableRestaurant(Integer restaurant_id);


    Collection<Buyable> findByRestaurantAndNameContains(Restaurant restaurant, String mc);
}
