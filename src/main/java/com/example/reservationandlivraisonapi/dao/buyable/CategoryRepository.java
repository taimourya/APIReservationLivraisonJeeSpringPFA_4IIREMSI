package com.example.reservationandlivraisonapi.dao.buyable;

import com.example.reservationandlivraisonapi.entity.buyable.Category;
import com.example.reservationandlivraisonapi.entity.buyable.DrinkCategory;
import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import com.example.reservationandlivraisonapi.entity.buyable.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT f from FoodCategory f")
    Collection<FoodCategory> findAllFoodCategories();

    @Query("SELECT d from DrinkCategory d")
    Collection<DrinkCategory> findAllDrinkCategories();
}
