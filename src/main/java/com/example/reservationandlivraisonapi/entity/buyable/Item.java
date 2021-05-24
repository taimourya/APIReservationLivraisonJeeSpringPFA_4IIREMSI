package com.example.reservationandlivraisonapi.entity.buyable;


import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ITEM", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class Item extends Buyable {


    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Collection<MenuItem> menuItems = new ArrayList<>();

    public Item(Integer id, String name, float price, String image, Restaurant restaurant) {
        super(id, name, price, image, restaurant, null);
        this.menuItems = menuItems;
    }
}