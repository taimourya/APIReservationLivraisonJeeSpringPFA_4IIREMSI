package com.example.reservationandlivraisonapi.dao.acteurs;

import com.example.reservationandlivraisonapi.entity.acteurs.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //diagrame de navigation

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    @Query("SELECT l from Livreur l")
    Collection<Livreur> findAllLivreurs();

    @Query("SELECT c from Client c")
    Collection<Client> findAllClients();

    @Query("SELECT d from Directeur d")
    Collection<Directeur> findAllDirecteurs();

    @Query("SELECT a from Administrateur a")
    Collection<Administrateur> findAllAdministrateurs();

    @Query("SELECT r from Restaurant r")
    Collection<Restaurant> findAllRestaurants();

    @Query("SELECT a from Assistant a")
    Collection<Assistant> findAllAssistants();

    @Query("SELECT a from AssistantExpert a")
    Collection<AssistantExpert> findAllAssistantsExperts();

}
