package com.daniele.mydatabase.userProfile.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.daniele.mydatabase.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mydatabase.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.Comment_;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails_;

@Repository
public class CommentDaoImpl extends BaseEntityDaoImpl<Comment> implements CommentDao {

    @Override
    public List<Comment> findCommentsByUser(String name) {
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserProfileDetails> query = builder.createQuery(UserProfileDetails.class);
        Root<Comment> root = query.from(Comment.class);
        Join<Comment, UserProfileDetails> join = root.join(Comment_.user);
        query.select(join).where(builder.equal(root.get(Comment_.user).get(UserProfileDetails_.name), name));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    @Override
    public List<Comment> findCommentsWithJooq(String name) {
    	/*DSLContext create = DSL.using(connection, SQLDialect.H2);
    	
    	Result<?> result = create.select()
                .from(UserComment)
                .join(UserProfile)
                .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
                .fetch();
    	*/
    	return null;
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
