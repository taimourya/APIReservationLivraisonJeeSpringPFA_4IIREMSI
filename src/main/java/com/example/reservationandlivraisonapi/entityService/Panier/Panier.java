package com.example.reservationandlivraisonapi.entityService.Panier;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Panier {

    private Map<Integer, Integer> items = new HashMap<>();

}
