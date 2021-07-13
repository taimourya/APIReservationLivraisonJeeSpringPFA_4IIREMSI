package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.buyable.Category;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("REST")
public class Restaurant extends EntrepriseInfo {

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private Collection<Buyable> buyables = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private Collection<Category> categories = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private Collection<Reservation> reservations = new ArrayList<>();

    public Restaurant(Integer user_id, String username, String password, String ville, String adresse, String name, String phone) {
        super(user_id, username, password, ville, adresse, name, phone);
    }

    public Restaurant(Integer user_id, String username, String password, String ville, String adresse, float latitude, float longitude, String name, String phone) {
        super(user_id, username, password, null, ville, adresse, latitude, longitude, name, phone);
    }
}