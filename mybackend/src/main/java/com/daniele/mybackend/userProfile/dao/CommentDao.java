package com.daniele.mybackend.userProfile.dao;

import com.daniele.mybackend.shared.dao.BaseEntityDao;
import com.daniele.mydatabase.userProfile.model.Comment;

import java.util.List;

import org.jooq.Record2;

public interface CommentDao extends BaseEntityDao<Comment> {
    List<Comment> findCommentsByUser(String email);
    List<Record2<String, String>> findCommentsWithJooq(String email);
    List<Comment> findAllComments();
}