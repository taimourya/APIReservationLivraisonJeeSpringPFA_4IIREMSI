package com.example.reservationandlivraisonapi.dao.commande;

import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {

    @Query("SELECT l FROM Livraison l WHERE l.client = ?1 AND l.stat < 3")
    Collection<Livraison> findByClientEnCours(Client client);

}
