package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO ordersDAO;

	@InjectMocks
	private OrdersController controller;

	@Test
	public void OrdersControllerConstructorTest() {
		assertNotNull(controller);
		assertTrue(controller instanceof OrdersController);
	}

	@Test
	public void readAllTest() {
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders());

		Mockito.when(ordersDAO.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(ordersDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void createTest() {
		final Long C_ID = 1l;
		final Long I_ID = 1l;
		final Orders createdOrder = new Orders(C_ID, I_ID);

		Mockito.when(utils.getLong()).thenReturn(C_ID, I_ID);
		Mockito.when(ordersDAO.create(createdOrder)).thenReturn(createdOrder);

		assertEquals(createdOrder, controller.create());
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(ordersDAO, Mockito.times(1)).create(createdOrder);
	}

	@Test
	public void updateTest() {
		Orders updated = new Orders(1l, 1l, 2l);

		Mockito.when(utils.getLong()).thenReturn(updated.getId(), updated.getItemToRemove(), updated.getFirstItemId());
		Mockito.when(ordersDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, controller.update());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(ordersDAO, Mockito.times(1)).update(updated);
	}

	@Test
	public void deleteTest() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(ordersDAO.delete(ID)).thenReturn(1);

		assertEquals(1L, controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(ordersDAO, Mockito.times(1)).delete(ID);
	}
}
