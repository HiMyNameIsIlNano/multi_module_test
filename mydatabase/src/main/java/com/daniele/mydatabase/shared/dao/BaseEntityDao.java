package com.daniele.mydatabase.shared.dao;

import java.util.List;

public interface BaseEntityDao<T> {
	public T findById(Long id);
	public List<T> findAll();
	public Long count();
	public void save(T entity);
	public void update(T entity);
	public void delete(Long id);
}