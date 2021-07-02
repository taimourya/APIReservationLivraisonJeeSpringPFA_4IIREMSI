package com.example.reservationandlivraisonapi.metier.restaurant;


import com.example.reservationandlivraisonapi.entity.buyable.*;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;

import java.util.Collection;
import java.util.List;

public interface IRestaurantMetier {


	public Drink addDrink(int restaurant_id, String name, float price, String categoryName) throws Exception;
	public DrinkCategory addDrinkCategory(String name, int restaurant_id) throws Exception;
	public Food addFood(int restaurant_id, String name, float price, String categoryName) throws Exception;
	public FoodCategory addFoodCategory(String name, int restaurant_id) throws Exception;
	public Menu addMenu(int restaurant_id, String name, float price, List<Integer> items) throws Exception;
	public Buyable consulterBuyable(int id) throws Exception;
	public Collection<Buyable> conuslterListBuyable(int restaurant_id, String mc) throws Exception;
	public Collection<FoodCategory> conuslterFoodCategories(int restaurant_id) throws Exception;
	public Collection<DrinkCategory> conuslterDrinkCategories(int restaurant_id) throws Exception;
	public void deleteBuyable(int id);
	public void deleteCategory(String nom);
	public void editCategory(Category category);
	public void editItem(Item item);
	public void editMenu(Menu menu, List<Item> items);
	public Collection<Commande> historiqueCommande(int restaurant_id);
	public Collection<Livraison> livraisonEnCours(int restaurant_id);
	public Collection<Reservation> reservationEnCours(int restaurant_id) throws Exception;
	public void changerLocalisation(int restaurant_id, float latitude, float longitude) throws Exception;
	public Collection<Food> getFoodsByMc(int restaurant_id, String mc) throws Exception;
	public Collection<Drink> getDrinksByMc(int restaurant_id, String mc) throws Exception;
	public Collection<Menu> getMenusByMc(int restaurant_id, String mc) throws Exception;

}