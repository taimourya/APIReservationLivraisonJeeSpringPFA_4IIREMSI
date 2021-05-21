package com.example.reservationandlivraisonapi;

import com.example.reservationandlivraisonapi.dao.UserRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Administrateur;
import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Directeur;
import com.example.reservationandlivraisonapi.entity.acteurs.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ReservationAndLivraisonApiApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationAndLivraisonApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new Livreur(null, "livreur1", "123", "livreur1",
                "livreur1","cin", "ville", "adrss", "taimourya@gmail.com" ,
                "+21243334135", new Date(), new Date(), "moto"));



        userRepository.findAllDirecteurs().forEach(user -> System.out.println(user.getUsername()));


    }
}
