package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import com.example.reservationandlivraisonapi.entity.buyable.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
