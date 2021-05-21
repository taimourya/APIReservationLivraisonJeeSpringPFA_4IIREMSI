package com.example.reservationandlivraisonapi.entity.acteurs;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ADMIN", discriminatorType = DiscriminatorType.STRING, length = 4)
@DiscriminatorValue("ADMI")
public class Administrateur extends ParticulierInfo {

    public Administrateur(Integer user_id, String username, String password, String firstname, String lastname, String CIN, String ville, String adresse, String email, String phone, Date dateNaissance) {
        super(user_id, username, password, firstname, lastname, CIN, ville, adresse, email, phone, dateNaissance);
    }
}