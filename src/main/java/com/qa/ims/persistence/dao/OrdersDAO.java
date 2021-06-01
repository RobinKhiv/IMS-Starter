package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * from "
						+ "(SELECT order_items.fk_order_id AS order_id, order_items.fk_item_id AS item_id, item.item_name, item.item_price  "
						+ "FROM order_items JOIN item " + "ON order_items.fk_item_id = item.id) AS o  " 
						+ "JOIN orders "
						+ "ON o.order_id = orders.id "
						+ "ORDER BY o.order_id");) {
			Orders orders;
			List<Orders> order = new ArrayList<Orders>();

			do {
				orders = modelFromResultSet(resultSet);
				order.add(orders);
			} while (!resultSet.isAfterLast());

			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Orders read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("Select * from "
						+ "(SELECT order_item.fk_order_id AS order_id, order_items.fk_item_id AS item_id, item.item_name, item.item_price "
						+ "FROM order_items WHERE id = ? " + "JOIN item " + "ON order_items.fk_item_id = item.id) AS o "
						+ "JOIN orders ON o.order_id = orders.id");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			return modelFromResultSet(resultSet);

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		// TODO Auto-generated method stub
		return null;
	}

	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			Long newOrderID = resultSet.getLong("id");

			return new Orders(newOrderID, new ArrayList<>());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();) {
			PreparedStatement statement;
			statement = connection.prepareStatement("INSERT INTO orders(fk_customer_id) VALUES(?)");
			statement.setLong(1, order.getCustomerId());
			statement.executeUpdate();

			Orders latestOrder = readLatest();
			statement = connection.prepareStatement("INSERT INTO order_items(fk_order_id, fk_item_id) VALUES (?,?)");
			statement.setLong(1, latestOrder.getId());
			statement.setLong(2, order.getFirstItemId());
			statement.executeUpdate();

			return latestOrder;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders update(Orders order) {

		try (Connection connection = DBUtils.getInstance().getConnection();) {
			PreparedStatement statement;
			if (order.hasItem()) {
				statement = connection
						.prepareStatement("INSERT INTO order_items(fk_order_id, fk_item_id) VALUES (?,?)");
				statement.setLong(1, order.getId());
				statement.setLong(2, order.getFirstItemId());
				statement.executeUpdate();
				LOGGER.info("Added itemId " + order.getFirstItemId() + " to Order#" + order.getId());
			}

			if (order.getItemToRemove() != 0l) {
				statement = connection
						.prepareStatement("DELETE FROM order_items WHERE fk_order_id = ? AND fk_item_id = ?");
				statement.setLong(1, order.getId());
				statement.setLong(2, order.getItemToRemove());
				statement.executeUpdate();
				LOGGER.info("Deleted ItemID " + order.getId() + " from Order " + order.getItemToRemove());
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE fk_order_id = ?");){
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		List<Item> itemSet = new ArrayList<Item>();
		Long currentOrder = 0l;
		Long currentOrderSaved = 0l;
		Long currentCusID = 0l;
		double item_price = 0d;
		String item_name = "";

		while (resultSet.next()) {
			currentOrder = resultSet.getLong("order_id");
			item_name = resultSet.getString("item_name");
			item_price = resultSet.getDouble("item_price");

			if (currentOrderSaved == 0l)
				currentOrderSaved = currentOrder;

			else if (currentOrder != currentOrderSaved) {
				resultSet.previous();
				return new Orders(currentOrderSaved, currentCusID, itemSet);
			}

			currentCusID = resultSet.getLong("fk_customer_id");
			itemSet.add(new Item(item_name, item_price));
		}

		if (currentOrderSaved != 0l)
			return new Orders(currentOrderSaved, currentCusID, itemSet);
		else
			return new Orders();
	}

}
