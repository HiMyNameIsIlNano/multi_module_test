package com.daniele.mydatabase.userProfile.dao;

import com.daniele.mydatabase.shared.dao.BaseEntityDao;
import com.daniele.mydatabase.userProfile.model.Comment;

import java.util.List;

public interface CommentDao extends BaseEntityDao<Comment> {
    List<Comment> findCommentsByUser(String email);
    List<Comment> findAllComments();
}
