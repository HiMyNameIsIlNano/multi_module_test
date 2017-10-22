package com.daniele.mybackend.service.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.daniele.mybackend.service.BaseEntityService;
import com.daniele.mydatabase.dao.BaseEntityDao;

public class BaseEntityServiceImpl<T> implements BaseEntityService<T> {
	
	@Inject
	BaseEntityDao<T> baseEntityDao;
	
	@Override
	@Transactional
	public Long count() {
		return baseEntityDao.count();
	}
	
	@Override
	@Transactional
	public void save(T entity) {
		baseEntityDao.save(entity);
	}
	
	@Override
	@Transactional
	public void update(T entity) {
		baseEntityDao.update(entity);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		baseEntityDao.delete(id);
	}
}