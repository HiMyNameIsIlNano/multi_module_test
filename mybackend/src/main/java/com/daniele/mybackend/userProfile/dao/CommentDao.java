package com.daniele.mybackend.userProfile.dao;

import com.daniele.mybackend.shared.dao.BaseEntityDao;
import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mydatabase.userProfile.model.Comment;
import org.jooq.Record;

import java.util.List;

public interface CommentDao extends BaseEntityDao<Comment> {
    List<Record> findByFilter(CommentFilter filter);
}
