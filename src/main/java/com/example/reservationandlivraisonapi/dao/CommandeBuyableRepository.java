package com.example.reservationandlivraisonapi.dao;

import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommandeBuyableRepository extends JpaRepository<CommandeBuyable, Integer> {

    @Query("SELECT l from Livraison l")
    Collection<Livraison> findAllLivraisons();

    @Query("SELECT r from Reservation r")
    Collection<Reservation> findAllReservations();
}
