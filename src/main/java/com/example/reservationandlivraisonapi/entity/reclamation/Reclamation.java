package com.example.reservationandlivraisonapi.entity.reclamation;


import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reclamation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String message;
	private int stat;
	private Date date;
	@ManyToOne
	private User user;
	@ManyToOne
	private Assistant assistant;

	@OneToOne
	private Conversation conversation;


}