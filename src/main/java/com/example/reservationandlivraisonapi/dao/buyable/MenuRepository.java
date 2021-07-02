package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Food;
import com.example.reservationandlivraisonapi.entity.buyable.Item;
import com.example.reservationandlivraisonapi.entity.buyable.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Collection<Menu> findByNameContainsAndRestaurant(String mc, Restaurant restaurant);

}
