package com.example.reservationandlivraisonapi.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm implements Serializable {

    private String username;
    private String password;
    private String passwordConfirm;

}
