package com.qa.ims.persistence.domain;

import java.util.Set;

public class Orders {
	
	private Long orderId;
	private String customerName;
	private Set<Item> orderItems;
	
	public Orders(String customer) {
		this.setCustomerName(customer);
	}
	public Orders(Long orderId, Set<Item> items) {
		this.setId(orderId);
		this.setOrderItems(items);
	}
	public Orders(Long id, String customerName, Set<Item> items) {
		this.setId(id);
		this.setCustomerName(customerName);
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
	
	public Set<Item> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(Set<Item> items) {
		this.orderItems = items;
	}
	
	public String getName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Override
	public String toString() {
		String itemList = "[ ";
		
		for(Item item: orderItems)
			itemList = itemList + item.getItemName() + ", ";
		
		itemList = itemList.substring(0, itemList.length() - 2);
		itemList+= " ]";
				
		return "Order Id:" + orderId + " Items:" + itemList;
	}
	public double getTotalCost() {
		double total = 0d;
		
		for(Item item: orderItems)
			total += item.getItemPrice();
		
		return total;
	}
}
