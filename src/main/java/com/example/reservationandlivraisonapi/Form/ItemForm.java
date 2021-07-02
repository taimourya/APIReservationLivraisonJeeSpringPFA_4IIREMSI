package com.example.reservationandlivraisonapi.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm implements Serializable {
    private int restaurant_id;
    private String name;
    private float price;
    private String categoryName;
    private String type;
}
