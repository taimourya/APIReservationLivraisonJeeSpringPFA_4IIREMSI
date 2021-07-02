package com.example.reservationandlivraisonapi.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationForm implements Serializable {
    private int user_id;
    private String message;
}
