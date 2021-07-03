package com.example.reservationandlivraisonapi.dao.commande;

import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommandeBuyableRepository extends JpaRepository<CommandeBuyable, Integer> {

    @Query("SELECT c FROM CommandeBuyable c WHERE c.client = ?1 AND c.buyable = ?2")
    CommandeBuyable findBuyableClientPanier(Client client, Buyable buyable);

    //for panier
    void deleteAllByClient(Client c);

    @Query("SELECT c FROM CommandeBuyable c WHERE c.client = ?1")
    Collection<CommandeBuyable> getPanier(Client client);


}
