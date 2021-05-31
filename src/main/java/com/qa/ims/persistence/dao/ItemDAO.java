package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;

public class ItemDAO implements Dao<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public List<item> readAll() {
		// TODO Auto-generated method stub
		return null;
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
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public item modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	public item modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
