package com.daniele.mybackend.userProfile.service;

import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mydatabase.userProfile.model.Comment;
import org.jooq.Record;

import java.util.List;

public interface CommentService {
    Comment store(Comment comment);
    List<Record> getByFilter(CommentFilter filter);
}
