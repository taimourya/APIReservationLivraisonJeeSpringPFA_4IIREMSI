package com.example.reservationandlivraisonapi.entity.reclamation;


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
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String content;
	private Date date;

	@ManyToOne
	private Conversation conversation;
	@ManyToOne
	private User sender;


}