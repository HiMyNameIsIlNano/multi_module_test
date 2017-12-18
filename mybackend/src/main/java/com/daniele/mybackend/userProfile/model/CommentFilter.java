package com.daniele.mybackend.userProfile.model;

import java.time.LocalDate;

public class CommentFilter {

    private LocalDate validFrom;
    private LocalDate validTo;
    private boolean active;
    private String userName;

    public CommentFilter(LocalDate validFrom, LocalDate validTo, boolean active, String userName) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.active = active;
        this.userName = userName;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public boolean isActive() {
        return active;
    }

    public String getUserName() {
        return userName;
    }
}
