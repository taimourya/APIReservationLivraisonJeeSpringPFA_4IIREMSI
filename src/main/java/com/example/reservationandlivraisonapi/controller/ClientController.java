package com.example.reservationandlivraisonapi.controller;

import com.example.reservationandlivraisonapi.Form.CodePromoForm;
import com.example.reservationandlivraisonapi.Form.LivraisonForm;
import com.example.reservationandlivraisonapi.Form.ReservationForm;
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


    //@GetMapping("/buyable")

    @GetMapping("/client/codePromo")
    public String getCodePromos(@RequestParam int client_id) throws Exception {
        return clientMetier.getCodePromo(client_id);
    }

    @PostMapping("/client/use/codePromo")
    public Paraignage useCodePormos(@RequestBody CodePromoForm codePromoForm) throws Exception {
        return clientMetier.useCodePromo(codePromoForm.getClient_id(), codePromoForm.getCodePromo());
    }
    @GetMapping("/client/historique")
    public Collection<Commande> getHistorique(@RequestParam int client_id) throws Exception {
        return clientMetier.consulterHistorique(client_id);
    }
    @GetMapping("/client/reservationEnCours")
    public Collection<Reservation> getReservationEnCours(@RequestParam int client_id) throws Exception {
        return clientMetier.consulterReservationEnCours(client_id);
    }
    @GetMapping("/client/livraisonEnCours")
    public Collection<Livraison> getLivraisonEnCours(@RequestParam int client_id) throws Exception {
        return clientMetier.consulterLivraisonEnCours(client_id);
    }

    @GetMapping("/menu")
    public Collection<Buyable> getMenu(@RequestParam(required = false, defaultValue = "") String mc) {
        return clientMetier.consulterMenu(mc);
    }

    @GetMapping("/restaurant/proche")
    public Collection<Restaurant> getRestaurantsProche(@RequestParam float latitude, @RequestParam float longitude) {
        return clientMetier.consulterRestaurantProche(longitude, latitude);
    }


    @GetMapping("/restaurant/menu")
    public Collection<Buyable> getMenuRestaurant(
            @RequestParam int restaurant_id,
            @RequestParam(required = false, defaultValue = "") String mc
    ) throws Exception {

        return clientMetier.consulterMenuRestaurant(restaurant_id, mc);
    }

    @GetMapping("/panier")
    public PanierWithInfo panierOperation(
            @RequestParam int client_id,
            @RequestParam String operation,
            @RequestParam(required = false, defaultValue = "0") int buyable_id,
            @RequestParam(required = false, defaultValue = "1") int qtn
    ) throws Exception {

        if(buyable_id == 0 && !operation.equalsIgnoreCase("get") && !operation.equalsIgnoreCase("clear"))
            throw new Exception("vous devez specifier le buyable");
        if(operation.equalsIgnoreCase("add")) {
            clientMetier.ajouterAuPanier(buyable_id, qtn, client_id);
        }
        else if(operation.equalsIgnoreCase("del")) {
            clientMetier.supprimerDuPanier(buyable_id, client_id);
        }
        else if(operation.equalsIgnoreCase("rem")) {
            clientMetier.removeQtnPanier(buyable_id, qtn, client_id);
        }
        else if(operation.equalsIgnoreCase("clear")) {
            clientMetier.viderPanier(client_id);
        }
        else if(operation.equalsIgnoreCase("get")) {
            return clientMetier.consulterPanier(client_id);
        }
        else {
            throw new Exception("operation invalide");
        }
        return null;
    }
    @GetMapping("/panier/total")
    public float getTotalPanier(@RequestParam int client_id) throws Exception {
        return clientMetier.totalPanier(client_id);
    }

    @PostMapping("/commande/reservation")
    public Reservation comanderReservation(@RequestBody ReservationForm reservationForm) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateReservation = dateFormat.parse(reservationForm.getDateReservation());
        return clientMetier.commanderReservation(
                reservationForm.getClient_id(),
                dateReservation
        );
    }
    @PostMapping("/commande/livraison")
    public Livraison commanderLivraison(@RequestBody LivraisonForm livraisonForm) throws Exception {

        return clientMetier.commanderLivraison(
                livraisonForm.getClient_id(),
                livraisonForm.getLongitude(),
                livraisonForm.getLatitude()
        );
    }

    @GetMapping("/note/restaurant")
    private Note noterRestaurant(
            @RequestParam int client_id,
            @RequestParam int restaurant_id,
            @RequestParam String message,
            @RequestParam int etoile
    ) throws Exception {
        return clientMetier.noterRestaurant(client_id, restaurant_id, message, etoile);
    }
    @GetMapping("/note/livreur")
    private Note noterLivreur(
            @RequestParam int client_id,
            @RequestParam int livreur_id,
            @RequestParam String message,
            @RequestParam int etoile
    ) throws Exception {
        return clientMetier.noterLivreur(client_id, livreur_id, message, etoile);
    }



}
