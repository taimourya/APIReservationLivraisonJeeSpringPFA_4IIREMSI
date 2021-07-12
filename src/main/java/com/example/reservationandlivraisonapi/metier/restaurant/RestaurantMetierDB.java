package com.example.reservationandlivraisonapi.metier.restaurant;

import com.example.reservationandlivraisonapi.dao.acteurs.RestaurantRepository;
import com.example.reservationandlivraisonapi.dao.buyable.*;
import com.example.reservationandlivraisonapi.dao.commande.CommandeRepository;
import com.example.reservationandlivraisonapi.dao.commande.LivraisonRepository;
import com.example.reservationandlivraisonapi.dao.commande.ReservationRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.*;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class RestaurantMetierDB implements IRestaurantMetier{

    @Autowired
    IUserMetier userMetier;

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    BuyableRepository buyableRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    FoodCategoryRepository foodCategoryRepository;
    @Autowired
    DrinkCategoryRepository drinkCategoryRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    LivraisonRepository livraisonRepository;


    @Override
    public Drink addDrink(String name, float price, String categoryName) throws Exception {
        DrinkCategory category = addDrinkCategory(categoryName);
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        Drink drink = new Drink(null, name, price, "image", restaurant, category);
        return buyableRepository.save(drink);
    }

    @Override
    public DrinkCategory addDrinkCategory(String name) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return categoryRepository.save(new DrinkCategory(name, restaurant));
    }

    @Override
    public Food addFood(String name, float price, String categoryName) throws Exception {

        FoodCategory category = addFoodCategory(categoryName);
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        Food food = new Food(null, name, price, "image", restaurant, category);
        return buyableRepository.save(food);
    }

    @Override
    public FoodCategory addFoodCategory(String name) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return categoryRepository.save(new FoodCategory(name, restaurant));
    }

    @Override
    public Menu addMenu(String name, float price, List<Integer> items) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        Menu menu = new Menu(null, name, price, "image", restaurant);

        menu = buyableRepository.save(menu);

        for (Integer item_id : items) {
            Item item = itemRepository.findById(item_id).get();
            MenuItem menuItem = new MenuItem(null, menu, item);
            menuItemRepository.save(menuItem);
        }
        return menu;
    }

    @Override
    public Buyable consulterBuyable(int id) throws Exception{

        Optional<Buyable> buyableOptional = buyableRepository.findById(id);
        if (buyableOptional.isPresent()) {

            return buyableOptional.get();
        }

        throw new Exception("Buyable introuvable");
    }

    @Override
    public Collection<Buyable> conuslterListBuyable(String mc) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return buyableRepository.findByRestaurantAndNameContains(restaurant, mc);
    }

    @Override
    public Collection<Buyable> conuslterListBuyable(int restaurant_id, String mc) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterUser(restaurant_id);
        return buyableRepository.findByRestaurantAndNameContains(restaurant, mc);
    }

    @Override
    public Collection<FoodCategory> conuslterFoodCategories() throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return foodCategoryRepository.findByRestaurant(restaurant);
    }

    @Override
    public Collection<DrinkCategory> conuslterDrinkCategories() throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return drinkCategoryRepository.findByRestaurant(restaurant);
    }

    @Override
    public void deleteBuyable(int id) {
        buyableRepository.deleteById(id);
    }

    @Override
    public void deleteCategory(String nom) {
        categoryRepository.deleteById(nom);
    }

    @Override
    public void editCategory(Category category) {

    }

    @Override
    public void editItem(Item item) {

    }

    @Override
    public void editMenu(Menu menu, List<Item> items) {

    }

    @Override
    public Collection<Commande> historiqueCommande(int restaurant_id) {
        return null;
    }

    @Override
    public Collection<Livraison> livraisonEnCours(int restaurant_id) {
        return null;
    }

    @Override
    public Collection<Reservation> reservationEnCours() throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return reservationRepository.findByRestaurantAndDateReservationAfter(restaurant, new Date());
    }

    @Override
    public void changerLocalisation(float latitude, float longitude) throws Exception {

        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Collection<Food> getFoodsByMc(String mc) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return foodRepository.findByNameContainsAndRestaurant(mc, restaurant);
    }

    @Override
    public Collection<Drink> getDrinksByMc(String mc) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return drinkRepository.findByNameContainsAndRestaurant(mc, restaurant);
    }

    @Override
    public Collection<Menu> getMenusByMc(String mc) throws Exception {
        Restaurant restaurant = (Restaurant)userMetier.consulterAuthentificateUser();
        return menuRepository.findByNameContainsAndRestaurant(mc, restaurant);
    }
}
