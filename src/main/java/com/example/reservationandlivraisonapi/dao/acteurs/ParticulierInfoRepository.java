package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ParticulierInfoRepository extends JpaRepository<ParticulierInfo, Integer> {



}
