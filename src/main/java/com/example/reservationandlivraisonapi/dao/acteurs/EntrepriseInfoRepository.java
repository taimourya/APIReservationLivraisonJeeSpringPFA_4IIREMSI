package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.EntrepriseInfo;
import com.example.reservationandlivraisonapi.entity.acteurs.ParticulierInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseInfoRepository extends JpaRepository<EntrepriseInfo, Integer> {



}
