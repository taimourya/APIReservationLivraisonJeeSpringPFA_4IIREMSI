package com.example.reservationandlivraisonapi.entity.reclamation;


import com.example.reservationandlivraisonapi.entity.acteurs.Assistant;
import com.example.reservationandlivraisonapi.entity.acteurs.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//public User m_User;
	//public Reclamation m_Reclamation;

	@ManyToOne
	private User user;
	@ManyToOne
	private Assistant assistant;
	@OneToMany(mappedBy = "conversation")
	private Collection<Message> messages;


}