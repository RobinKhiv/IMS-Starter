package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
	@BeforeEach
	public void setup() {
		DBUtils.connect("dbTest.properties");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "test_item", 10.00d));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "test_item", 10.00d), DAO.read(ID));
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2l, "test item", 10.00d);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "item update", 10.25);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
