package com.example.reservationandlivraisonapi.Form;

import com.example.reservationandlivraisonapi.entity.buyable.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuForm implements Serializable {
    private String name;
    private float price;
    private List<Integer> items;
}
