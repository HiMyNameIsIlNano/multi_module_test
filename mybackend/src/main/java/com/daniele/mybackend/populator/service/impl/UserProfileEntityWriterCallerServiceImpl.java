package com.daniele.mybackend.populator.service.impl;

import com.daniele.mybackend.populator.service.UserProfileEntityWriterService;
import com.daniele.mybackend.populator.model.UserProfileWriterData;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserProfileEntityWriterCallerServiceImpl {

    @Inject
    public UserProfileEntityWriterCallerServiceImpl(UserProfileEntityWriterService writerService) {
        UserProfileWriterData data = UserProfileWriterData.of(5);
        writerService.populate(data);
    }

}
