package com.example.reservationandlivraisonapi;

import com.example.reservationandlivraisonapi.dao.acteurs.UserRepository;
import com.example.reservationandlivraisonapi.dao.buyable.*;
import com.example.reservationandlivraisonapi.dao.commande.CommandeBuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.CommandeRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.*;
import com.example.reservationandlivraisonapi.entity.buyable.*;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(ReservationAndLivraisonApiApplication.class, args);
    }

    public static String generateRandomString(int len) {
        String chars = "ABCDEFGHIJKLMNO PQRSTUVWXYZ abcdefghijk"
                +"lmnop qrstuvwxyz ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    @Override
    public void run(String... args) throws ParseException {

        String pass = encoder.encode("123");
        userRepository.save(new Client(null, "yahya", pass, "yahya",
                "taimourya","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), "abcd123abcd"));

        userRepository.save(new Livreur(null, "diyaa", pass, "diyaa",
                "benyas","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date(), "moto"));

        userRepository.save(new Assistant(null, "hamza", pass, "hamza",
                "Elhattab","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date()));

        userRepository.save(new Administrateur(null, "Administrateur", pass, "Administrateur",
                "Administrateur","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date()));

        userRepository.save(new Restaurant(null, "Tacos", pass, "ville", "adr",
                33.3333f, 7.55665f,
                "Tacos De lyon","+21243334135" ));




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



        String[] restnames = {
                "MC Donalds", "Burger King", "Cosamiya", "Mamo food",
                "Ahmed chef", "Nkoa", "Atelier Oriental", "Riad restaurant",
                "Iloli", "Boccaccio", "Don Camillo", "Le petit rocher",
                "Beach mama", "Basmane", "ICebeery", "Kabuki",
                "KFC", "Luigi", "Cosamiya", "Nespresso",
        };
        double[] lats = {
                33.333, 33.44334, 34.52452424, 35.524254254245,
                33.52452245, 33.254254254, 33.52454254254, 33.254282252,
                33.52452245, 33.254254254, 33.52454254254, 33.254282252,
                33.9838063, 33.3389383, 33.2542336, 33.254254254,
                33.368386389, 33.32398383, 33.3893893, 33.8389389398,
        };
        double[] longs = {
                -7.365356, -7.32662626, -7.362662626, -7.262662626,
                -7.236535365, -7.365356, -7.5422542, -7.39838386,
                -7.254254242, -7.254254254, -7.839893983, -7.938252533,
                -7.393825245, -7.636524545, -7.233452542, -7.542425432,
                -7.398252542, -7.385442454, -7.225245256, -7.245254245,
        };
        for(int i = 1; i < restnames.length + 1; i++) {
            userRepository.save(new Client(null, "cli"+i, pass, "cli"+i,
                    "cli"+i,"cin", "ville", "adrss", "taimourya@gmail.com" ,
                    "+21243334135", new Date(), "abcd123abcd"+i));

            userRepository.save(new Livreur(null, "liv"+i, pass, "liv"+i,
                    "liv"+i,"cin", "ville", "adrss", "taimourya@gmail.com" ,
                    "+21243334135", new Date(), new Date(), "moto"));

            userRepository.save(new Assistant(null, "assi"+i, pass, "assi"+i,
                    "assi"+i,"cin", "ville", "adrss", "taimourya@gmail.com" ,
                    "+21243334135", new Date(), new Date()));

            userRepository.save(new Administrateur(null, "admin"+i, pass, "admin"+i,
                    "admin"+i,"cin", "ville", "adrss", "taimourya@gmail.com" ,
                    "+21243334135", new Date()));


            Restaurant r = userRepository.save(new Restaurant(null, "rest"+i, pass, "ville", "adr",
                    (float) lats[i-1], (float) longs[i-1],
                    restnames[i-1], "+21243334135" ));
            for(int j = 0; j < 5; j++) {
                DrinkCategory dc = categoryRepository.save(
                        categoryRepository.save(
                                new DrinkCategory("drinkcat"+(i+j)*i, r)
                        )
                );
                for(int k = 0; k < 15; k++) {
                    buyableRepository.save(
                            new Drink(
                                    null, generateRandomString((int)(Math.random() * 8)),
                                    (float) (Math.random() * 100),
                                    "image",
                                    r,
                                    dc
                            )
                    );
                }

                FoodCategory fc = categoryRepository.save(
                        categoryRepository.save(
                                new FoodCategory("foodcat"+(i+j)*i, r)
                        )
                );

                for(int k = 0; k < 15; k++) {
                    buyableRepository.save(
                            new Food(
                                    null, generateRandomString((int)(Math.random() * 8)),
                                    (float) (Math.random() * 100),
                                    "image",
                                    r,
                                    fc
                            )
                    );
                }
            }
        }










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
