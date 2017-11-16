package com.daniele.mydatabase.shared.dao.impl;

import org.springframework.stereotype.Repository;

import com.daniele.mydatabase.shared.dao.DummyDao;

@Repository
public class DummyDaoImpl implements DummyDao {

	@Override
	public String getDummyValue() {
		return "Dummy";
	}

}
