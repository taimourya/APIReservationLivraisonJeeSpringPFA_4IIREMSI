package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.Form.CodePromoForm;
import com.example.reservationandlivraisonapi.Form.LivraisonForm;
import com.example.reservationandlivraisonapi.Form.RegisterForm;
import com.example.reservationandlivraisonapi.Form.ReservationForm;
import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entity.note.Note;
import com.example.reservationandlivraisonapi.entity.paraignage.Paraignage;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;
import com.example.reservationandlivraisonapi.metier.client.IClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
@CrossOrigin()
public class ClientController {

    @Autowired
    IClientMetier clientMetier;


    @GetMapping("/register")
    public Client register(@RequestBody RegisterForm registerForm) throws Exception {

        return clientMetier.inscription(registerForm.getUsername(), registerForm.getPassword(), registerForm.getPasswordConfirm());
    }


    @GetMapping("/client/codePromo")
    public String getCodePromos() throws Exception {
        return clientMetier.getCodePromo();
    }

    @PostMapping("/client/use/codePromo")
    public Paraignage useCodePormos(@RequestBody CodePromoForm codePromoForm) throws Exception {
        return clientMetier.useCodePromo(codePromoForm.getCodePromo());
    }
    @GetMapping("/client/historique")
    public Collection<Commande> getHistorique() throws Exception {
        return clientMetier.consulterHistorique();
    }
    @GetMapping("/client/reservationEnCours")
    public Collection<Reservation> getReservationEnCours() throws Exception {
        return clientMetier.consulterReservationEnCours();
    }
    @GetMapping("/client/livraisonEnCours")
    public Collection<Livraison> getLivraisonEnCours() throws Exception {
        return clientMetier.consulterLivraisonEnCours();
    }

    @GetMapping("client/menu")
    public Collection<Buyable> getMenu(@RequestParam(required = false, defaultValue = "") String mc) {
        return clientMetier.consulterMenu(mc);
    }

    @GetMapping("/client/restaurant/proche")
    public Collection<Restaurant> getRestaurantsProche(@RequestParam float latitude, @RequestParam float longitude) {
        return clientMetier.consulterRestaurantProche(longitude, latitude);
    }


    @GetMapping("/client/restaurant/menu")
    public Collection<Buyable> getMenuRestaurant(
            @RequestParam int restaurant_id,
            @RequestParam(required = false, defaultValue = "") String mc
    ) throws Exception {

        return clientMetier.consulterMenuRestaurant(restaurant_id, mc);
    }

    @GetMapping("/client/panier")
    public PanierWithInfo panierOperation(
            @RequestParam String operation,
            @RequestParam(required = false, defaultValue = "0") int buyable_id,
            @RequestParam(required = false, defaultValue = "1") int qtn
    ) throws Exception {

        if(buyable_id == 0 && !operation.equalsIgnoreCase("get") && !operation.equalsIgnoreCase("clear"))
            throw new Exception("vous devez specifier le buyable");
        if(operation.equalsIgnoreCase("add")) {
            clientMetier.ajouterAuPanier(buyable_id, qtn);
        }
        else if(operation.equalsIgnoreCase("del")) {
            clientMetier.supprimerDuPanier(buyable_id);
        }
        else if(operation.equalsIgnoreCase("rem")) {
            clientMetier.removeQtnPanier(buyable_id, qtn);
        }
        else if(operation.equalsIgnoreCase("clear")) {
            clientMetier.viderPanier();
        }
        else if(operation.equalsIgnoreCase("get")) {
            return clientMetier.consulterPanier();
        }
        else {
            throw new Exception("operation invalide");
        }
        return null;
    }
    @GetMapping("/client/panier/total")
    public float getTotalPanier() throws Exception {
        return clientMetier.totalPanier();
    }

    @PostMapping("/client/commande/reservation")
    public Reservation comanderReservation(@RequestBody ReservationForm reservationForm) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateReservation = dateFormat.parse(reservationForm.getDateReservation());
        return clientMetier.commanderReservation(
                dateReservation
        );
    }
    @PostMapping("/client/commande/livraison")
    public Livraison commanderLivraison(@RequestBody LivraisonForm livraisonForm) throws Exception {

        return clientMetier.commanderLivraison(
                livraisonForm.getLongitude(),
                livraisonForm.getLatitude()
        );
    }

    @GetMapping("/client/note/restaurant")
    private Note noterRestaurant(
            @RequestParam int restaurant_id,
            @RequestParam String message,
            @RequestParam int etoile
    ) throws Exception {
        return clientMetier.noterRestaurant(restaurant_id, message, etoile);
    }
    @GetMapping("/client/note/livreur")
    private Note noterLivreur(
            @RequestParam int livreur_id,
            @RequestParam String message,
            @RequestParam int etoile
    ) throws Exception {
        return clientMetier.noterLivreur(livreur_id, message, etoile);
    }



}
