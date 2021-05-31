package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM item");){
			List<Item> items = new ArrayList<Item>();
			while(resultSet.next())
				items.add(modelFromResultSet(resultSet));
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Item>();
	}

	@Override
	public Item read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public item read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public item create(item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public item update(item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String item_name = resultSet.getString("item_name");
		double item_price = resultSet.getBigDecimal("item_price").doubleValue();
		return new Item(id, item_name, item_price);
	}

}
