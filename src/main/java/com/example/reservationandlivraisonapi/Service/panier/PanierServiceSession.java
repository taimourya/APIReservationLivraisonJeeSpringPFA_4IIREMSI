package com.example.reservationandlivraisonapi.Service.panier;

import com.example.reservationandlivraisonapi.dao.buyable.BuyableRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entityService.Panier.ItemPanierInfo;
import com.example.reservationandlivraisonapi.entityService.Panier.Panier;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.*;


public class PanierServiceSession implements IPanierService {

    @Autowired
    HttpSession session;
    @Autowired
    BuyableRepository buyableRepository;

    @Override
    public void add(int buyable_id, int qtn, int id) {
        System.out.println(session.getId() + " : " + session.getCreationTime());
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null)
            panier = new Panier();
        if(panier.getItems().containsKey(buyable_id)) {
            int newQtn = panier.getItems().get(buyable_id) + qtn;
            panier.getItems().put(buyable_id, newQtn);
        }
        else {
            panier.getItems().put(buyable_id, qtn);
        }
        session.setAttribute("panier", panier);
    }

    @Override
    public void clear(int id) {
        System.out.println(session.getId() + " : " + session.getCreationTime());
        session.removeAttribute("panier");
    }

    @Override
    public void remove(int buyable_id, int qtn, int id) throws Exception {
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier != null)
        {
            if(panier.getItems().containsKey(buyable_id)) {
                System.out.println(session.getId() + " : " + session.getCreationTime());
                int newQtn = panier.getItems().get(buyable_id) - qtn;
                if(newQtn <= 0) {
                    panier.getItems().remove(buyable_id);
                }
                else {
                    panier.getItems().put(buyable_id, newQtn);
                }
            }
            session.setAttribute("panier", panier);
            return;
        }
        throw new Exception("buyable introuvable");
    }

    @Override
    public void delete(int buyable_id, int id) throws Exception {
        System.out.println(session.getId() + " : " + session.getCreationTime());
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier != null) {
            if (panier.getItems().containsKey(buyable_id)) {
                panier.getItems().remove(buyable_id);
                return;
            }
        }
        throw new Exception("buyable introuvable");
    }

    @Override
    public Livraison toLivraison(Client client, float longitude, float latitude) throws Exception {
        System.out.println(session.getId() + " : " + session.getCreationTime());
        Panier panier = (Panier) session.getAttribute("panier");

        if(panier != null) {
            Livraison livraison = new Livraison(null, new Date(), client, new ArrayList<>(), null, 0, longitude, latitude, null);

            for (Map.Entry<Integer, Integer> item : panier.getItems().entrySet()) {
                Optional<Buyable> buyableOptional = buyableRepository.findById(item.getKey());
                if(buyableOptional.isPresent()) {
                    CommandeBuyable commandeBuyable = new CommandeBuyable(null, item.getValue(), livraison, buyableOptional.get());
                    livraison.getCommandeBuyables().add(commandeBuyable);
                }
            }
            clear(client.getUser_id());
            return livraison;
        }
        throw  new Exception("panier vide");
    }

    @Override
    public Reservation toReservation(Client client, Date dateReservation) throws Exception{
        System.out.println(session.getId() + " : " + session.getCreationTime());
        Panier panier = (Panier) session.getAttribute("panier");

        if(panier != null) {
            Reservation reservation = new Reservation(null, new Date(), client, new ArrayList<>(), null/*late*/, dateReservation);

            for (Map.Entry<Integer, Integer> item : panier.getItems().entrySet()) {
                Optional<Buyable> buyableOptional = buyableRepository.findById(item.getKey());
                if(buyableOptional.isPresent()) {
                    CommandeBuyable commandeBuyable = new CommandeBuyable(null, item.getValue(), reservation, buyableOptional.get());
                    reservation.getCommandeBuyables().add(commandeBuyable);
                }
            }
            clear(client.getUser_id());
            return reservation;
        }

        throw  new Exception("panier vide");
    }


    @Override
    public PanierWithInfo toPanierWithInfo(int id) throws Exception {
        System.out.println(session.getId() + " : " + session.getCreationTime());
        Panier panier = (Panier) session.getAttribute("panier");
        PanierWithInfo panierWithInfo = new PanierWithInfo();
        if(panier != null) {

            for (Map.Entry<Integer, Integer> item : panier.getItems().entrySet()) {
                Optional<Buyable> buyableOptional = buyableRepository.findById(item.getKey());
                if(buyableOptional.isPresent()) {
                    Buyable buyable = buyableOptional.get();

                    panierWithInfo.getItems().add(
                            new ItemPanierInfo(
                                    buyable.getId(),
                                    buyable.getName(),
                                    buyable.getRestaurant().getName(),
                                    buyable.getPrice(),
                                    item.getValue()
                            )
                    );

                }
                else {
                    throw new Exception("probleme au niveau du conversion du panier with info");
                }
            }
            return panierWithInfo;
        }

        throw new Exception("panier vide");
    }

    @Override
    public float getTotal(int id) {
        return 0;
    }
}
