package com.daniele.mybackend.userProfile.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.generated.tables.UserComment;
import org.jooq.generated.tables.UserProfile;
import org.springframework.stereotype.Repository;

import com.daniele.mybackend.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mylogger.LogMethodExecution;

@Repository
public class CommentDaoImpl extends BaseEntityDaoImpl<Comment> implements CommentDao {

	@Inject
	private DSLContext create;
	
    @Override
    public List<Comment> findCommentsByUser(String name) {
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
		Root<Comment> root = query.from(Comment.class);
		query.where(builder.equal(root.get(Comment_.user).get(UserProfileDetails_.name), name));
		return getEntityManager().createQuery(query).getResultList();
    }
    
    @Override
    @LogMethodExecution
    public List<Record2<String,String>> findCommentsWithJooq(String name) {
    	UserComment c = UserComment.USER_COMMENT.as("c");
    	UserProfile p = UserProfile.USER_PROFILE.as("p");
    	
    	Result<Record2<String, String>> result =
    	        create.select(c.COMMENT_TEXT, c.COMMENT_TOPIC)
    	              .from(c)
    	              .join(p).on(c.USER_ID_REF.eq(p.ID))
    	              .where(p.NAME.eq(name))
    	              .fetch();

    	return result;
    }

    @Override
    public List<Comment> findAllComments() {
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root<Comment> root = query.from(Comment.class);
        query.orderBy(builder.asc(root.get("lastUpdate")));
        return getEntityManager().createQuery(query).getResultList();
    }
}
