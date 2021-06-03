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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;
	
	@Mock ItemDAO itemDAO;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void ItemControllerConstructorTest() {
		assertNotNull(controller);
		assertTrue(controller instanceof ItemController);	
	}
	
	@Test
	public void readAllTest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("keyboard", 10.25d));

		Mockito.when(itemDAO.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(itemDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void createTest() {
		final String I_NAME = "keyboard";
		final double I_PRICE = 10d;
		final Item createdItem = new Item(I_NAME, I_PRICE);
		
		Mockito.when(utils.getString()).thenReturn(I_NAME);
		Mockito.when(utils.getDouble()).thenReturn(I_PRICE);
		Mockito.when(itemDAO.create(createdItem)).thenReturn(createdItem);
		
		assertEquals(createdItem, controller.create());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemDAO, Mockito.times(1)).create(createdItem);
	}

	@Test
	public void updateTest() {
		
	}

	@Test
	public void deleteTest() {
		
	}
}
