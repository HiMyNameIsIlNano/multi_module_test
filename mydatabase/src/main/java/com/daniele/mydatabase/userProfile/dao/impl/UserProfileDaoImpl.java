package com.daniele.mydatabase.userProfile.dao.impl;

import com.daniele.mydatabase.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mydatabase.userProfile.dao.UserProfileDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.Comment_;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails_;
import com.daniele.mylogger.LogExecutionTime;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

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
