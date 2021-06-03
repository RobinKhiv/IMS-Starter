package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
	}

	@Test
	public void testRead() {
		
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(1l, "test item", 10.00d);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDelete() {

	}
}
