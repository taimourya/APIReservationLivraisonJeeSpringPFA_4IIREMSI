package com.example.reservationandlivraisonapi.dao.paraignage;

import com.example.reservationandlivraisonapi.entity.paraignage.Paraignage;
import com.example.reservationandlivraisonapi.entity.reclamation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParaignageRepository extends JpaRepository<Paraignage, Integer> {

}
