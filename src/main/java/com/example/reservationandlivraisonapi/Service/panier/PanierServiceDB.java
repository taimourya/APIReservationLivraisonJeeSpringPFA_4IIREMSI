package com.example.reservationandlivraisonapi.Service.panier;

import com.example.reservationandlivraisonapi.dao.buyable.BuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.CommandeBuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.CommandeRepository;
import com.example.reservationandlivraisonapi.dao.commande.LivraisonRepository;
import com.example.reservationandlivraisonapi.dao.commande.ReservationRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entityService.Panier.ItemPanierInfo;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PanierServiceDB implements IPanierService {

    @Autowired
    IUserMetier userMetier;

    @Autowired
    private CommandeBuyableRepository commandeBuyableRepository;
    @Autowired
    private BuyableRepository buyableRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;


    @Override
    public void add(int buyable_id, int qtn, int id) throws Exception {

        Client client = (Client)userMetier.consulterUser(id);
        Optional<Buyable> buyableOptional = buyableRepository.findById(buyable_id);
        if(!buyableOptional.isPresent())
            throw new Exception("buyable introuvable");
        Buyable buyable = buyableOptional.get();
        CommandeBuyable item = commandeBuyableRepository.findBuyableClientPanier(client, buyable);
        if(item == null) {
            item = new CommandeBuyable(null, qtn, null, buyable, client);
        }
        else {
            item.setQtn(item.getQtn() + qtn);
        }
        commandeBuyableRepository.save(item);
    }

    @Override
    public void clear(int id) throws Exception {
        Client client = (Client)userMetier.consulterUser(id);
        commandeBuyableRepository.deleteAllByClient(client);
    }

    @Override
    public void remove(int buyable_id, int qtn, int id) throws Exception {
        Client client = (Client)userMetier.consulterUser(id);
        Optional<Buyable> buyableOptional = buyableRepository.findById(buyable_id);
        if(!buyableOptional.isPresent())
            throw new Exception("buyable introuvable");
        Buyable buyable = buyableOptional.get();
        CommandeBuyable item = commandeBuyableRepository.findBuyableClientPanier(client, buyable);
        if(item != null) {
            item.setQtn(item.getQtn() - qtn);
            if(item.getQtn() <= 0) {
                commandeBuyableRepository.delete(item);
                return;
            }
            commandeBuyableRepository.save(item);
        }
        throw new Exception("item introuvable");
    }

    @Override
    public void delete(int buyable_id, int id) throws Exception {
        Client client = (Client)userMetier.consulterUser(id);
        Optional<Buyable> buyableOptional = buyableRepository.findById(buyable_id);
        if(!buyableOptional.isPresent())
            throw new Exception("buyable introuvable");
        Buyable buyable = buyableOptional.get();
        CommandeBuyable item = commandeBuyableRepository.findBuyableClientPanier(client, buyable);
        if(item != null) {
            commandeBuyableRepository.delete(item);
            return;
        }
        throw new Exception("item introuvable");
    }

    @Override
    public Livraison toLivraison(Client client, float longitude, float latitude) throws Exception {

        Collection<CommandeBuyable> commandeBuyables = commandeBuyableRepository.getPanier(client);
        Livraison livraison = new Livraison(null, new Date(), client, commandeBuyables,
                null, 0, longitude, latitude, null);
        livraisonRepository.save(livraison);
        for (CommandeBuyable c : commandeBuyables) {
            c.setClient(null);
            c.setCommande(livraison);
            commandeBuyableRepository.save(c);
        }

        return livraisonRepository.save(livraison);
    }

    @Override
    public Reservation toReservation(Client client, Date dateReservation) throws Exception {

        List<CommandeBuyable> commandeBuyables = (List<CommandeBuyable>) commandeBuyableRepository.getPanier(client);
        if(commandeBuyables.size() == 0)
            throw new Exception("panier vide");
        Restaurant restaurant = commandeBuyables.get(0).getBuyable().getRestaurant();
        Reservation reservation  = new Reservation(null, new Date(), client, new ArrayList<>(), restaurant, dateReservation);
        reservationRepository.save(reservation);
        for (CommandeBuyable c : commandeBuyables) {
            if(c.getBuyable().getRestaurant().getUser_id() != restaurant.getUser_id())
                throw new Exception("une reservation doit contenir des items d'un meme restaurant");
            c.setClient(null);
            c.setCommande(reservation);
            commandeBuyableRepository.save(c);
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public PanierWithInfo toPanierWithInfo(int id) throws Exception {
        Client client = (Client)userMetier.consulterUser(id);
        PanierWithInfo panierWithInfo = new PanierWithInfo();

        Collection<CommandeBuyable> commandeBuyables = commandeBuyableRepository.getPanier(client);

        for (CommandeBuyable c : commandeBuyables) {
            panierWithInfo.getItems().add(
                    new ItemPanierInfo(
                        c.getBuyable().getId(),
                        c.getBuyable().getName(),
                        c.getBuyable().getRestaurant().getName(),
                        c.getBuyable().getPrice(),
                        c.getQtn()
                    )
            );
        }

        return panierWithInfo;
    }

    @Override
    public float getTotal(int id) throws Exception {
        Client client = (Client)userMetier.consulterUser(id);
        PanierWithInfo panierWithInfo = new PanierWithInfo();

        Collection<CommandeBuyable> commandeBuyables = commandeBuyableRepository.getPanier(client);

        float s = 0;
        for (CommandeBuyable c : commandeBuyables) {
            s += (c.getBuyable().getPrice() * c.getQtn());
        }

        return s;
    }
}
