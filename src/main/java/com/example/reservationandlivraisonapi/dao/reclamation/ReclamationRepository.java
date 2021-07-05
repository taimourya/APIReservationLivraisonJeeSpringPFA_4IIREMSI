package com.example.reservationandlivraisonapi.dao.reclamation;

import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import com.example.reservationandlivraisonapi.entity.reclamation.Message;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {

    @Query("SELECT r FROM Reclamation r WHERE r.assistant = ?1 AND r.stat = 1")
    Reclamation findCurrentReclamation(Assistant assistant);

    @Query("SELECT r FROM Reclamation r WHERE r.stat < 1")
    Reclamation findReclamationNonPris();

}
