package com.botree.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CardBrands {
	
	@Id
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CardBrands(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CardBrands() {
		super();
	}
	
	

}
