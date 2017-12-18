package com.daniele.mybackend.userProfile.service.impl;

import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mybackend.userProfile.repository.CommentRepository;
import com.daniele.mybackend.userProfile.service.CommentService;
import com.daniele.mydatabase.userProfile.model.Comment;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentDao commentDao;

    @Inject
    private CommentRepository commentRepository;

    @Transactional
    public Comment store(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public List<Record> getByFilter(CommentFilter filter) {
        return commentDao.findByFilter(filter);
    }
}
