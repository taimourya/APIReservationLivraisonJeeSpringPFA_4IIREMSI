package com.example.reservationandlivraisonapi.entity.buyable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

	@Id
	private Integer id;
	@ManyToOne
	private Buyable menu;
	@ManyToOne
	private Buyable item;

}