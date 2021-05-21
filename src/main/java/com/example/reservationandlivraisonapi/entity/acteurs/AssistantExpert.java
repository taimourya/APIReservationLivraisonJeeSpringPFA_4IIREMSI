package com.example.reservationandlivraisonapi.entity.acteurs;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("ASIX")
public class AssistantExpert extends Assistant {
    public AssistantExpert(Integer user_id, String username, String password, String firstname, String lastname, String CIN, String ville, String adresse, String email, String phone, Date dateNaissance, Date dateContrat) {
        super(user_id, username, password, firstname, lastname, CIN, ville, adresse, email, phone, dateNaissance, dateContrat);
    }
}