package com.example.reservationandlivraisonapi.entity.commande;


import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMMANDE", discriminatorType = DiscriminatorType.STRING, length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	@ManyToOne
	private Client client;
	@OneToMany(mappedBy = "commande", fetch = FetchType.EAGER)
	private Collection<CommandeBuyable> commandeBuyables = new ArrayList<>();


}