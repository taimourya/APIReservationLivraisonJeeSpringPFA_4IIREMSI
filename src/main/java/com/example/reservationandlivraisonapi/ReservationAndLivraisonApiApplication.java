package com.example.reservationandlivraisonapi;

import com.example.reservationandlivraisonapi.dao.acteurs.UserRepository;
import com.example.reservationandlivraisonapi.dao.buyable.*;
import com.example.reservationandlivraisonapi.dao.commande.CommandeBuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.CommandeRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.*;
import com.example.reservationandlivraisonapi.entity.buyable.Drink;
import com.example.reservationandlivraisonapi.entity.buyable.DrinkCategory;
import com.example.reservationandlivraisonapi.entity.buyable.Food;
import com.example.reservationandlivraisonapi.entity.buyable.FoodCategory;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@Transactional
public class ReservationAndLivraisonApiApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BuyableRepository buyableRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    CommandeBuyableRepository commandeBuyableRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationAndLivraisonApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {


        userRepository.save(new Client(null, "cli1", "123", "client1",
                "client1","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), "abcd123abcd"));

        userRepository.save(new Livreur(null, "livreur1", "123", "livreur1",
                "livreur1","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date(), "moto"));

        userRepository.save(new Assistant(null, "Assistant", "123", "Assistant",
                "Assistant","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date()));

        userRepository.save(new Administrateur(null, "Administrateur", "123", "Administrateur",
                "Administrateur","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date()));

        userRepository.save(new Restaurant(null, "rest1", "123", "ville", "adr",
                "restaunt1","+21243334135" ));
        userRepository.save(new Restaurant(null, "rest2", "123", "ville", "adr",
                "restaunt2","+21243334135" ));
        userRepository.save(new Restaurant(null, "rest3", "123", "ville", "adr",
                "restaunt3","+21243334135" ));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int restaurant_id = 5;

        categoryRepository.save(new DrinkCategory("Soda", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new DrinkCategory("Jus", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new DrinkCategory("Cafe", (Restaurant) userRepository.findById(restaurant_id).get()));

        categoryRepository.save(new FoodCategory("Tacos", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new FoodCategory("Sandwitch", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new FoodCategory("Hamburger", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new FoodCategory("Plas", (Restaurant) userRepository.findById(restaurant_id).get()));
        categoryRepository.save(new FoodCategory("Dessert", (Restaurant) userRepository.findById(restaurant_id).get()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        buyableRepository.save(new Drink(null, "coca cola", 5, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (DrinkCategory) categoryRepository.findById("Soda").get()));
        buyableRepository.save(new Drink(null, "Fanta", 5, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (DrinkCategory) categoryRepository.findById("Soda").get()));
        buyableRepository.save(new Drink(null, "Pepsi", 5, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (DrinkCategory) categoryRepository.findById("Soda").get()));
        buyableRepository.save(new Drink(null, "Jus orrange", 10, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (DrinkCategory) categoryRepository.findById("Jus").get()));


        buyableRepository.save(new Food(null, "Tacos nuget", 28, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (FoodCategory) categoryRepository.findById("Tacos").get()));
        buyableRepository.save(new Food(null, "Tacos poulet", 25, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (FoodCategory) categoryRepository.findById("Tacos").get()));
        buyableRepository.save(new Food(null, "Tacos viande hacher", 25, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (FoodCategory) categoryRepository.findById("Tacos").get()));
        buyableRepository.save(new Food(null, "Chesy burger", 23, "image",
                (Restaurant) userRepository.findById(restaurant_id).get(),
                (FoodCategory) categoryRepository.findById("Hamburger").get()));


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////




        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        commandeRepository.save(new Livraison(null, dateFormat.parse("2021-07-03 12:24:30"),
                (Client) userRepository.findById(1).get(),
                new ArrayList<>(), new Date(), 3, 0, 0,
                (Livreur) userRepository.findById(2).get()
        ));

        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(1).get(),
                buyableRepository.findById(1).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(1).get(),
                buyableRepository.findById(5).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(1).get(),
                buyableRepository.findById(6).get()
        ));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        commandeRepository.save(new Reservation(null, new Date(),
                (Client) userRepository.findById(1).get(),
                new ArrayList<>(),
                (Restaurant) userRepository.findById(restaurant_id).get(),
                dateFormat.parse("2021-07-03 12:00:00"))
        );

        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(2).get(),
                buyableRepository.findById(1).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(2).get(),
                buyableRepository.findById(5).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(2).get(),
                buyableRepository.findById(6).get()
        ));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        commandeRepository.save(new Reservation(null, new Date(),
                (Client) userRepository.findById(1).get(),
                new ArrayList<>(),
                (Restaurant) userRepository.findById(restaurant_id).get(),
                dateFormat.parse("2021-07-03 15:00:00"))
        );

        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(3).get(),
                buyableRepository.findById(1).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(3).get(),
                buyableRepository.findById(5).get()
        ));
        commandeBuyableRepository.save(new CommandeBuyable(null, 4,
                commandeRepository.findById(3).get(),
                buyableRepository.findById(6).get()
        ));



        System.out.println("--------------------------- ALl users -----------------------------------");
        userRepository.findAll().forEach(user -> System.out.println(user.getUsername()));
        System.out.println("--------------------------- ALl livreurs -----------------------------------");
        userRepository.findAllLivreurs().forEach(user -> System.out.println(user.getUsername()));
        System.out.println("--------------------------- ALl assistant -----------------------------------");
        userRepository.findAllAssistants().forEach(user -> System.out.println(user.getUsername()));
        System.out.println("--------------------------- ALl restaurant -----------------------------------");
        userRepository.findAllRestaurants().forEach(user -> System.out.println(user.getUsername()));
        System.out.println("--------------------------- ALl client -----------------------------------");
        userRepository.findAllClients().forEach(user -> System.out.println(user.getUsername()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--------------------------- ALl categories -----------------------------------");
        categoryRepository.findAll().forEach(category -> System.out.println(category.getName()));
        System.out.println("--------------------------- Only food category -----------------------------------");
        categoryRepository.findAllFoodCategories().forEach(category -> System.out.println(category.getName()));
        System.out.println("--------------------------- Only drink category -----------------------------------");
        categoryRepository.findAllDrinkCategories().forEach(category -> System.out.println(category.getName()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--------------------------- All buyables  -----------------------------------");
        buyableRepository.findAll().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Only Food  -----------------------------------");
        buyableRepository.findAllFood().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Only Drink  -----------------------------------");
        buyableRepository.findAllDrink().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Drink of Soda Category  -----------------------------------");
        ((DrinkCategory) categoryRepository.findById("Soda").get()).getItems().forEach(food -> System.out.println(food.getName()));
        System.out.println("--------------------------- Food of Tacos Category  -----------------------------------");
        ((FoodCategory) categoryRepository.findById("Tacos").get()).getItems().forEach(food -> System.out.println(food.getName()));

        System.out.println("--------------------------- All Buyables of Restaurant1  -----------------------------------");
        buyableRepository.findBuyableRestaurant(userRepository.findById(5).get().getUser_id()).forEach(food -> System.out.println(food.getName()));
        try {
            ((Restaurant) userRepository.findById(5).get()).getBuyables().forEach(food -> System.out.println(food.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--------------------------- All commandes  -----------------------------------");
        commandeRepository.findAll().forEach(commande -> System.out.println(commande.getClient().getUsername()));
        System.out.println("--------------------------- commande 1 buyables  -----------------------------------");
        commandeRepository.findById(1).get().getCommandeBuyables().forEach(commandeBuyable ->
                System.out.println(commandeBuyable.getBuyable().getName())
        );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--------------------------- food of rest1 by mc  -----------------------------------");
        foodRepository.findByNameContainsAndRestaurant("cos", (Restaurant) userRepository.findById(5).get())
                .forEach(
                        food ->
                                System.out.println(food.getName())
        );



        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");

    }
}
