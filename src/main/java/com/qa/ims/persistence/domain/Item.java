package com.qa.ims.persistence.domain;

public class Item {
	private Long id;
	private String itemName;
	private double itemPrice;
	
	public Item(Long id) {
		this(id," ", 0l);
	}
	public Item(String itemName, double itemPrice) {
		this(0l, itemName, itemPrice);
	}
	
	public Item(Long id, String itemName, double itemPrice) {
		this.setItemName(itemName);
		this.setItemPrice(itemPrice);
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	@Override
	public String toString() {
		return "id:" + id + " item name:" + itemName + " price:$" + itemPrice;
	}
	
}
