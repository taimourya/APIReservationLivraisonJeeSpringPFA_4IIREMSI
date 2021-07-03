package com.example.reservationandlivraisonapi.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationForm implements Serializable {

    private int client_id;
    private String dateReservation;
}
