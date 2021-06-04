package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrdersTest {

	@Test
	public void orderConstructorTest() {
		final Orders ord = new Orders();
		assertNotNull(ord);
		assertTrue(ord instanceof Orders);
	}
	@Test
	public void orderConstructorTest2() {
		final Orders ord = new Orders(1l,1l);
		assertNotNull(ord);
		assertTrue(ord instanceof Orders);
	}
	@Test
	public void orderConstructorTest3() {
		final Orders ord = new Orders(1l, new ArrayList<>());
		assertNotNull(ord);
		assertTrue(ord instanceof Orders);
	}
	@Test
	public void orderConstructorTest4() {
		final Orders ord = new Orders(1l, 1l,1l);
		assertNotNull(ord);
		assertTrue(ord instanceof Orders);
	}
	@Test
	public void orderConstructorTest5() {
		final Orders ord = new Orders(1l, 1l, new ArrayList<>());
		assertNotNull(ord);
		assertTrue(ord instanceof Orders);
	}
	@Test
	public void getIdTest()	{
		final Long expectedId = 1l;
		final Orders ord = new Orders(expectedId, new ArrayList<>());
		Long result = ord.getId();
		
		assertEquals(expectedId, result);
	}
	@Test 
	public void removeItemTest() {
		final Item i = new Item(1l, "test_item", 10.0d);
		List<Item> list = new ArrayList<Item>();
		list.add(i);
		list.add(i);
		
		List<Item> expectedList = new ArrayList<Item>();
		expectedList.add(i);
		
		Orders ord = new Orders(1l, list);
		ord.removeItem(i);
		
		Orders expected = new Orders(1l, expectedList);
		
		assertEquals(expected, ord);
		
	}
}
