package com.example.reservationandlivraisonapi;

import com.example.reservationandlivraisonapi.dao.BuyableRepository;
import com.example.reservationandlivraisonapi.dao.CategoryRepository;
import com.example.reservationandlivraisonapi.dao.UserRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.*;
import com.example.reservationandlivraisonapi.entity.buyable.Drink;
import com.example.reservationandlivraisonapi.entity.buyable.DrinkCategory;
import com.example.reservationandlivraisonapi.entity.buyable.Food;
import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ReservationAndLivraisonApiApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BuyableRepository buyableRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationAndLivraisonApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new Livreur(null, "livreur1", "123", "livreur1",
                "livreur1","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date(), "moto"));

        userRepository.save(new Assistant(null, "Assistant", "123", "Assistant",
                "Assistant","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date()));

        userRepository.save(new Administrateur(null, "Administrateur", "123", "Administrateur",
                "Administrateur","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date()));

        userRepository.save(new Restaurant(null, "restaurant1", "123", "ville", "adr",
                "restaunt1","+21243334135" ));

        categoryRepository.save(new DrinkCategory(null, "Soda"));
        categoryRepository.save(new DrinkCategory(null, "Jus"));
        categoryRepository.save(new DrinkCategory(null, "Cafe"));

        categoryRepository.save(new FoodCategory(null, "Tacos"));
        categoryRepository.save(new FoodCategory(null, "Sandwitch"));
        categoryRepository.save(new FoodCategory(null, "Hamburger"));
        categoryRepository.save(new FoodCategory(null, "Plas"));
        categoryRepository.save(new FoodCategory(null, "Dessert"));


        buyableRepository.save(new Drink(null, "coca cola", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (DrinkCategory) categoryRepository.findById(1).get()));
        buyableRepository.save(new Drink(null, "Fanta", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (DrinkCategory) categoryRepository.findById(1).get()));
        buyableRepository.save(new Drink(null, "Pepsi", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (DrinkCategory) categoryRepository.findById(1).get()));
        buyableRepository.save(new Drink(null, "Jus orrange", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (DrinkCategory) categoryRepository.findById(2).get()));


        buyableRepository.save(new Food(null, "Tacos nuget", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (FoodCategory) categoryRepository.findById(4).get()));
        buyableRepository.save(new Food(null, "Tacos poulet", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (FoodCategory) categoryRepository.findById(4).get()));
        buyableRepository.save(new Food(null, "Tacos viande hacher", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (FoodCategory) categoryRepository.findById(4).get()));
        buyableRepository.save(new Food(null, "Chesy burger", 5, "image",
                (Restaurant) userRepository.findById(4).get(),
                (FoodCategory) categoryRepository.findById(6).get()));


        System.out.println("--------------------------- ALl users -----------------------------------");
        userRepository.findAll().forEach(user -> System.out.println(user.getUsername()));
        System.out.println("--------------------------- ALl categories -----------------------------------");
        categoryRepository.findAll().forEach(category -> System.out.println(category.getName()));
        System.out.println("--------------------------- Only food category -----------------------------------");
        categoryRepository.findAllFoodCategories().forEach(category -> System.out.println(category.getName()));
        System.out.println("--------------------------- Only drink category -----------------------------------");
        categoryRepository.findAllDrinkCategories().forEach(category -> System.out.println(category.getName()));
        System.out.println("--------------------------- All buyables  -----------------------------------");
        buyableRepository.findAll().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Only Food  -----------------------------------");
        buyableRepository.findAllFood().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Only Drink  -----------------------------------");
        buyableRepository.findAllDrink().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Drink of Soda Category  -----------------------------------");
        ((DrinkCategory) categoryRepository.findById(1).get()).getItems().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Food of Tacos Category  -----------------------------------");
        ((FoodCategory) categoryRepository.findById(4).get()).getItems().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- All Buyables of Restaurant1  -----------------------------------");
        buyableRepository.findBuyableRestaurant(userRepository.findById(4).get().getUser_id()).forEach(food -> System.out.println(food.getName()));
        try {
            ((Restaurant) userRepository.findById(4).get()).getBuyables().forEach(food -> System.out.println(food.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
