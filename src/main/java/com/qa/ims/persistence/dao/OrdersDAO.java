package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders>  {
	
	public static final Logger LOGGER = LogManager.getLogger();
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items JOIN item ON order_items.fk_item_id = item.id");) {
			List<Orders> order = new ArrayList<>();
			Set<Item> itemSet = new HashSet<Item>();
			double item_price = 0d;
			String item_name = "";
			Long currentOrder = 0l;
			Long currentOrderSaved = 0l;
			
			while (resultSet.next()) {
				currentOrder = resultSet.getLong("fk_order_id");
				item_name = resultSet.getString("item_name");
				item_price = resultSet.getDouble("item_price");
				
				if(currentOrderSaved == 0l)
					currentOrderSaved = currentOrder;
				
				else if(currentOrder != currentOrderSaved) {
					Orders orders = new Orders(currentOrderSaved, itemSet);
					order.add(orders);
					itemSet.clear();
				}
				itemSet.add(new Item(item_name, item_price));	
			}
			Orders orders = new Orders(currentOrderSaved, itemSet);
			order.add(orders);
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Orders read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders create(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
