package com.example.reservationandlivraisonapi.entity.acteurs;


import com.example.reservationandlivraisonapi.entity.reclamation.Reclamation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_USER", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	private String username;
	@JsonIgnore
	private String password;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Collection<Reclamation> reclamations;

}