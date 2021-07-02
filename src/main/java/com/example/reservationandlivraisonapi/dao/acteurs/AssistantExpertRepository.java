package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import com.example.reservationandlivraisonapi.entity.acteurs.AssistantExpert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantExpertRepository extends JpaRepository<AssistantExpert, Integer> {



}
