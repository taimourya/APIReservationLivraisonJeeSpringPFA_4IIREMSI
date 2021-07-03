package com.example.reservationandlivraisonapi.entityService.Panier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPanierInfo {

    private int id;
    private String name;
    private String restaurantName;
    private float price;
    private int qtn;

}
