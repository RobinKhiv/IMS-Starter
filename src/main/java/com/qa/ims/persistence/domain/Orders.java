package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	
	private Long orderId;
	private Long customerId;
	private List<Item> orderItems;
	
	public Orders() {
		this(0l);
	}
	public Orders(Long customer) {
		this(customer, new ArrayList<Item>());
	}
	public Orders(Long orderId, List<Item> items) {
		this(orderId, 0l, items);
	}
	public Orders(Long id, Long customerId, List<Item> items) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setOrderItems(items);
	}
	
	public Long getId() {
		return orderId;
	}
	
	public void setId(Long id) {
		this.orderId = id;
	}
	
	public void addItem(Item item) {
		orderItems.add(item);
	}
	
	public void removeItem(Item item) {
		orderItems.remove(item);
	}
	
	public List<Item> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(List<Item> items) {
		this.orderItems = items;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		String itemList = "[ ";
		
		for(Item item: orderItems)
			itemList = itemList + item.getItemName() + ", ";
		
		if(itemList.length() > 2)
			itemList = itemList.substring(0, itemList.length() - 2);
		
		itemList+= " ]";
				
		return "Order Id:" + orderId + " CustomerId: " + customerId 
				+ " Items:" + itemList;
	}
	public double getTotalCost() {
		double total = 0d;
		
		for(Item item: orderItems)
			total += item.getItemPrice();
		
		return total;
	}
}
