package com.example.reservationandlivraisonapi.entity.paraignage;

import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paraignage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Client parraineur;
    @ManyToOne
    private Client parrainer;

}
