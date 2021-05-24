package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
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
    private Collection<Buyable> buyables;

    public Restaurant(Integer user_id, String username, String password, String ville, String adresse, String name, String phone) {
        super(user_id, username, password, ville, adresse, name, phone);
    }
}