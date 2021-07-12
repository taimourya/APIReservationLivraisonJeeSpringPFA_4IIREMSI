package com.example.reservationandlivraisonapi.metier.restaurant;


import com.example.reservationandlivraisonapi.entity.buyable.*;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;

import java.util.Collection;
import java.util.List;

public interface IRestaurantMetier {


	public Drink addDrink(String name, float price, String categoryName) throws Exception;
	public DrinkCategory addDrinkCategory(String name) throws Exception;
	public Food addFood(String name, float price, String categoryName) throws Exception;
	public FoodCategory addFoodCategory(String name) throws Exception;
	public Menu addMenu(String name, float price, List<Integer> items) throws Exception;
	public Buyable consulterBuyable(int id) throws Exception;
	public Collection<Buyable> conuslterListBuyable(String mc) throws Exception;
	public Collection<Buyable> conuslterListBuyable(int restaurant_id, String mc) throws Exception;
	public Collection<FoodCategory> conuslterFoodCategories() throws Exception;
	public Collection<DrinkCategory> conuslterDrinkCategories() throws Exception;
	public void deleteBuyable(int id);
	public void deleteCategory(String nom);
	public void editCategory(Category category);
	public void editItem(Item item);
	public void editMenu(Menu menu, List<Item> items);
	public Collection<Commande> historiqueCommande(int restaurant_id);
	public Collection<Livraison> livraisonEnCours(int restaurant_id);
	public Collection<Reservation> reservationEnCours() throws Exception;
	public void changerLocalisation(float latitude, float longitude) throws Exception;
	public Collection<Food> getFoodsByMc(String mc) throws Exception;
	public Collection<Drink> getDrinksByMc(String mc) throws Exception;
	public Collection<Menu> getMenusByMc(String mc) throws Exception;

}