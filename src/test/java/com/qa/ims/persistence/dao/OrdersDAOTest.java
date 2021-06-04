package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {

	private final OrdersDAO DAO = new OrdersDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect("dbTest.properties");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		Orders created = new Orders(1l, 1l);
		created.setId(2l);

		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Orders> expected = new ArrayList<>();

		List<Item> expectedItem = new ArrayList<>();
		expectedItem.add(new Item(1l, "test_item", 10.0d));

		Orders ord = new Orders(1l, 1l, expectedItem);
		expected.add(ord);

		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Orders(1l, 1l, new ArrayList<>()), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		final Item expectedItem = new Item(1l, "test_item", 10.0d);

		List<Item> itemList = new ArrayList<>();
		itemList.add(expectedItem);

		assertEquals(new Orders(ID, 1l, itemList), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Orders updated = new Orders(1l, 1l, 0l);
		final Orders updateDelete = new Orders(1l, 0l, 1l);

		List<Item> list = new ArrayList<Item>();
		Item i = new Item(1l, "test_item", 10.0d);
		list.add(i);
		list.add(i);

		final Orders expectedAdd = new Orders(1l, 1l, list);
		final Orders expectedDelete = new Orders(0l, 0l, new ArrayList<>());

		assertEquals(expectedAdd, DAO.update(updated));
		assertEquals(expectedDelete, DAO.update(updateDelete));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1l));
	}
}
