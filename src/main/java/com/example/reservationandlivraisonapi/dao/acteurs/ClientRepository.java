package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {



}
