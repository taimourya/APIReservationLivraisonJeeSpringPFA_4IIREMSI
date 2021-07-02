package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.Administrateur;
import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {



}
