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
    public Collection<Reservation> getReservations() throws Exception {
        return restaurantMetier.reservationEnCours();
    }

    @GetMapping("/restaurant/get/categories")
    public Collection<? extends Category> getFoodCategories(@RequestParam String type) throws Exception {
        if(type.equalsIgnoreCase("food")) {
            return restaurantMetier.conuslterFoodCategories();
        }
        else if(type.equalsIgnoreCase("drink")){
            return restaurantMetier.conuslterDrinkCategories();
        }
        return null;
    }

    @GetMapping("/restaurant/buyables")
    public Collection<? extends Buyable> getBuyables(
            @RequestParam(required = true) String type,
            @RequestParam(required = false, defaultValue = "") String mc
    ) throws Exception {

        if(type.equalsIgnoreCase("food")) {
            return restaurantMetier.getFoodsByMc(mc);
        }
        else if(type.equalsIgnoreCase("drink")){
            return restaurantMetier.getDrinksByMc(mc);
        }
        else if(type.equalsIgnoreCase("menu")){
            return restaurantMetier.getMenusByMc(mc);
        }
        else {
            return restaurantMetier.conuslterListBuyable(mc);
        }
    }


    @GetMapping("user/buyable")
    public Buyable getBuyable(@RequestParam int id) throws Exception {

        return restaurantMetier.consulterBuyable(id);
    }

    @PostMapping("restaurant/save/item")
    public Buyable saveItem(@RequestBody ItemForm itemForm) throws Exception {

        System.out.println("name : " + itemForm.getName());
        System.out.println("price : " + itemForm.getPrice());
        System.out.println("categoryName : " + itemForm.getCategoryName());
        System.out.println("type : " + itemForm.getType());

        if(itemForm.getType().equalsIgnoreCase("food")) {
            return restaurantMetier.addFood(itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryName());
        }
        else if(itemForm.getType().equalsIgnoreCase("drink")){
            return restaurantMetier.addDrink(itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryName());
        }
        throw new Exception("type not found");
    }

    @PostMapping("restaurant/save/menu")
    public Menu saveMenu(@RequestBody MenuForm menuForm) throws Exception {
        return restaurantMetier.addMenu(menuForm.getName(), menuForm.getPrice(), menuForm.getItems());
    }

    @GetMapping("/restaurant/localisation")
    public void localisation(@RequestParam float latitude, @RequestParam float longitude) throws Exception {
        restaurantMetier.changerLocalisation(latitude, longitude);
    }

}
