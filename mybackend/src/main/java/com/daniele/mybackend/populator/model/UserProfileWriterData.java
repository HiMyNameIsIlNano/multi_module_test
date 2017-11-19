package com.daniele.mybackend.populator.model;

import com.daniele.mybackend.shared.model.WriterServiceData;

public class UserProfileWriterData implements WriterServiceData {

    private int userNumber;

    private UserProfileWriterData(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public static UserProfileWriterData of(int userNumber) {
        return new UserProfileWriterData(userNumber);
    }
}
