package com.example.reservationandlivraisonapi.dao.commande;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    public Collection<Reservation> findByRestaurantAndDateReservationAfter(Restaurant restaurant, Date date);
}
