package com.example.reservationandlivraisonapi.dao;

import com.example.reservationandlivraisonapi.entity.buyable.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
}
