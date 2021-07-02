package com.example.reservationandlivraisonapi.entity.note;

import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Livreur;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private int etoile;

    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private Livreur livreur;
    @ManyToOne
    private Client auteur;


}
