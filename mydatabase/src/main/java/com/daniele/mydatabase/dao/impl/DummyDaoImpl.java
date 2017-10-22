package com.daniele.mydatabase.dao.impl;

import org.springframework.stereotype.Repository;

import com.daniele.mydatabase.dao.DummyDao;

@Repository
public class DummyDaoImpl implements DummyDao {

	@Override
	public String getDummyValue() {
		return "Dummy";
	}

}
