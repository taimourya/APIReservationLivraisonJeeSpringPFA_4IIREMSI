package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.Form.ItemForm;
import com.example.reservationandlivraisonapi.Form.MenuForm;
import com.example.reservationandlivraisonapi.dao.buyable.BuyableRepository;
import com.example.reservationandlivraisonapi.dao.buyable.DrinkRepository;
import com.example.reservationandlivraisonapi.entity.buyable.*;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.metier.restaurant.IRestaurantMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin()
public class RestaurantController {

    @Autowired
    IRestaurantMetier restaurantMetier;

    @GetMapping("/restaurant/reservationEnCours")
    public Collection<Reservation> getReservations(@RequestParam int restaurant_id) throws Exception {
        return restaurantMetier.reservationEnCours(restaurant_id);
    }

    @GetMapping("/restaurant/get/categories")
    public Collection<? extends Category> getFoodCategories(
            @RequestParam int restaurant_id,
            @RequestParam String type
    ) throws Exception {
        if(type.equalsIgnoreCase("food")) {
            return restaurantMetier.conuslterFoodCategories(restaurant_id);
        }
        else if(type.equalsIgnoreCase("drink")){
            return restaurantMetier.conuslterDrinkCategories(restaurant_id);
        }
        return null;
    }

    @GetMapping("/restaurant/buyables")
    public Collection<? extends Buyable> getBuyables(
            @RequestParam(required = true) int restaurant_id,
            @RequestParam(required = true) String type,
            @RequestParam(required = false, defaultValue = "") String mc
    ) throws Exception {

        if(type.equalsIgnoreCase("food")) {
            return restaurantMetier.getFoodsByMc(restaurant_id, mc);
        }
        else if(type.equalsIgnoreCase("drink")){
            return restaurantMetier.getDrinksByMc(restaurant_id, mc);
        }
        else if(type.equalsIgnoreCase("menu")){
            return restaurantMetier.getMenusByMc(restaurant_id, mc);
        }
        else {
            return restaurantMetier.conuslterListBuyable(restaurant_id, mc);
        }
    }


    @GetMapping("/buyable")
    public Buyable getBuyable(@RequestParam int id) throws Exception {

        return restaurantMetier.consulterBuyable(id);
    }

    @PostMapping("/save/item")
    public Buyable saveItem(@RequestBody ItemForm itemForm) throws Exception {

        System.out.println("rest id : " + itemForm.getRestaurant_id());
        System.out.println("name : " + itemForm.getName());
        System.out.println("price : " + itemForm.getPrice());
        System.out.println("categoryName : " + itemForm.getCategoryName());
        System.out.println("type : " + itemForm.getType());

        if(itemForm.getType().equalsIgnoreCase("food")) {
            return restaurantMetier.addFood(itemForm.getRestaurant_id(), itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryName());
        }
        else if(itemForm.getType().equalsIgnoreCase("drink")){
            return restaurantMetier.addDrink(itemForm.getRestaurant_id(), itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryName());
        }
        throw new Exception("type not found");
    }

    @PostMapping("/save/menu")
    public Menu saveMenu(@RequestBody MenuForm menuForm) throws Exception {
        return restaurantMetier.addMenu(menuForm.getRestaurant_id(), menuForm.getName(), menuForm.getPrice(), menuForm.getItems());
    }

    @GetMapping("/restaurant/localisation")
    public void localisation(@RequestParam int restaurant_id, @RequestParam float latitude, @RequestParam float longitude) throws Exception {
        restaurantMetier.changerLocalisation(restaurant_id, latitude, longitude);
    }

}
