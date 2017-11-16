package com.daniele.mybackend.shared.service;

public interface BaseEntityService<T> {
	public Long count();
	public void save(T entity);
	public void update(T entity);
	public void delete(Long id);
}