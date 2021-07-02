package com.example.reservationandlivraisonapi.entity.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("MENU")
public class Menu extends Buyable {

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Collection<MenuItem> menuItems = new ArrayList<>();

    public Menu(Integer id, String name, float price, String image, Restaurant restaurant) {
        super(id, name, price, image, restaurant, null);
        this.menuItems = new ArrayList<>();
    }
}