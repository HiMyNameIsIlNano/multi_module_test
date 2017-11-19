package com.daniele.mydatabase.shared.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.daniele.mydatabase.shared.dao.BaseEntityDao;

public class BaseEntityDaoImpl<T> implements BaseEntityDao<T> {
	
	@PersistenceContext
	private EntityManager entityManager;

	DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseEntityDaoImpl() {
         this.clazz = 
        		 (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	 }
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Inject
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public T findById(Long id) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> root = query.from(clazz);
		SingularAttribute<? super T, ?> _id = 
				getEntityManager().getMetamodel().entity(clazz).getSingularAttribute("id");
		query.where(builder.equal(root.get(_id), id));
		return getEntityManager().createQuery(query).getSingleResult();
	}
	
	@Override
	public List<T> findAll() {
		CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> userProfile = query.from(clazz);
		query.select(userProfile);
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@Override
	public Long count() {
		CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(clazz)));
		//query.where(/*time slice for sliced entities?*/);
		return entityManager.createQuery(query).getSingleResult();
	}
	
	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}
	
	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Long id) {
		T entity = entityManager.find(clazz, id);
		entityManager.remove(entity);
	}
}