package com.daniele.mybackend.userProfile.dao;

import com.daniele.mybackend.userProfile.model.CommentFilter;
import org.jooq.Record;

import java.util.List;

public interface CommentDao {
    List<Record> findByFilter(CommentFilter filter);
}
