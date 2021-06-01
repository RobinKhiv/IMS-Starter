package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrdersDAO ordersDAO;
	private Utils utils;
	
	public OrdersController(OrdersDAO orderDAO, Utils utils) {
		super();
		this.ordersDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Orders create() {
		LOGGER.info("Please enter a customer ID");
		Long id = utils.getLong();

		LOGGER.info("Please enter an Item ID to add to the order");
		Long itemId = utils.getLong();

		Orders order = ordersDAO.create(new Orders(id, itemId));
		return order;
	}

	@Override
	public Orders update() {
		LOGGER.info("Please Enter the Order ID you want to update");
		Long id = utils.getLong();
		LOGGER.info("id " + id);
		LOGGER.info("Please Enter the Item ID you want to remove from order " + id);
		LOGGER.info("Enter 0 if you do now want to remove an item");
		Long removeId = utils.getLong();
		
		LOGGER.info("Please enter the Item ID you want to add to order " + id);
		LOGGER.info("Please 0 if you do not want to add an item");
		Long addId = utils.getLong();
		
		Orders orders = ordersDAO.update(new Orders(id, addId, removeId));
		return orders;
	}

	@Override
	public int delete() {
		
		return 0;
	}

}
