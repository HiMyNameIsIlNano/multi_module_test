package com.daniele.mybackend.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.daniele.mybackend.service.DummyService;
import com.daniele.mydatabase.shared.dao.DummyDao;

@Service
public class DummyServiceImpl implements DummyService {

	@Inject
	private DummyDao dummyDao;
	
	@Override
	public String getDummy() {
		return dummyDao.getDummyValue();
	}
}
