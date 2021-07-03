package com.example.reservationandlivraisonapi.entityService.Panier;


import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanierWithInfo implements Serializable {

    private List<ItemPanierInfo> items = new ArrayList<>();
}
