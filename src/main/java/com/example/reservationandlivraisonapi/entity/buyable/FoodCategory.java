package com.example.reservationandlivraisonapi.entity.buyable;

import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("FOOD")
public class FoodCategory extends Category {


    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Collection<Food> items = new ArrayList<>();

    public FoodCategory(String name, Restaurant restaurant) {
        super(name, restaurant);
    }
}