package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ItemTest {
	
	@Test
	public void itemConstructorTest() {
		final Item i = new Item(1l);
		assertNotNull(i);
		assertTrue(i instanceof Item);
	}
	@Test
	public void itemConstructorTest2() {
		final Item i = new Item("item_name", 10.00d);
		assertNotNull(i);
		assertTrue(i instanceof Item);
	}
	@Test
	public void itemConstructorTest3() {
		final Item i = new Item(1l, "test_item", 10.00d);
		assertNotNull(i);
		assertTrue(i instanceof Item);
	}
}
