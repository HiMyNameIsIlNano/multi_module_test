package com.daniele.mybackend.userProfile.service.impl;

import com.daniele.mybackend.userProfile.service.CommentService;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentDao commentDao;

    @Override
    @Transactional
    public Long count() {
        return commentDao.count();
    }

    @Override
    @Transactional
    public void save(Comment entity) {
        commentDao.save(entity);
    }

    @Override
    @Transactional
    public void update(Comment entity) {
        commentDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentDao.delete(id);
    }
}
