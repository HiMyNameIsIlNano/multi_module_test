package com.daniele.mydatabase.userProfile.dao.impl;

import com.daniele.mydatabase.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mydatabase.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CommentDaoImpl extends BaseEntityDaoImpl<Comment> implements CommentDao {

    @Override
    public List<Comment> findCommentsByUser(String email) {
        List<Comment> allComments = findAllComments();
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root<UserProfileDetails> root = query.from(UserProfileDetails.class);
        Join<UserProfileDetails, Comment> join = root.join(UserProfileDetails_.comments);
        query.select(join).where(builder.equal(root.get(UserProfileDetails_.email), email));
        return getEntityManager().createQuery(query).getResultList();
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
