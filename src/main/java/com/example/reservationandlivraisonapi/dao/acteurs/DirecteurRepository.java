package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.Administrateur;
import com.example.reservationandlivraisonapi.entity.acteurs.Directeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirecteurRepository extends JpaRepository<Directeur, Integer> {



}
