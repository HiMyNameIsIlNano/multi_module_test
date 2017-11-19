package com.daniele.mybackend.shared.service.impl;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DbPopluatorServiceImpl {

    private static int USER_NUMBER = 5;
    private EntityWriterServiceImpl writerService;

    @Inject
    public DbPopluatorServiceImpl(EntityWriterServiceImpl writerService) {
        this.writerService = writerService;
        this.writerService.loadUsers(USER_NUMBER);
    }

}
