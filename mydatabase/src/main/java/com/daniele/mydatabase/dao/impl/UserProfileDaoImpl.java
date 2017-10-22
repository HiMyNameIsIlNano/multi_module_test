package com.daniele.mydatabase.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.daniele.mydatabase.dao.UserProfileDao;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails_;
import com.daniele.mylogger.LogExecutionTime;

@Repository
public class UserProfileDaoImpl extends BaseEntityDaoImpl<UserProfileDetails> implements UserProfileDao {
	
	@Override
	public List<UserProfileDetails> findByName(String name) {
		CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserProfileDetails> query = builder.createQuery(UserProfileDetails.class);
		Root<UserProfileDetails> root = query.from(UserProfileDetails.class);
		query.where(builder.like(root.get(UserProfileDetails_.name), name));
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@Override
	@LogExecutionTime
	public List<UserProfileDetails> findAllAndSortBySurnameAsc() {
		CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserProfileDetails> query = builder.createQuery(UserProfileDetails.class);
		Root<UserProfileDetails> root = query.from(UserProfileDetails.class);
		query.orderBy(builder.asc(root.get("surname")));
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public UserProfileDetails findByEmail(String email) {
		CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserProfileDetails> query = builder.createQuery(UserProfileDetails.class);
		Root<UserProfileDetails> root = query.from(UserProfileDetails.class);
		query.where(builder.equal(root.get(UserProfileDetails_.email), email));
		return getEntityManager().createQuery(query)
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);
	}
}
