package com.daniele.mybackend.userProfile.model;

import com.daniele.mydatabase.shared.model.SortType;
import com.daniele.mydatabase.userProfile.model.UserRole;

import java.time.LocalDate;

public class UserProfileDetailsFilter {

    private LocalDate validFrom;
    private LocalDate validTo;
    private boolean active;
    private String name;
    private String surname;
    private String email;
    private String nickname;
    private UserRole userRole;
    private SortType sort;

    public UserProfileDetailsFilter() {
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public SortType getSort() {
        return sort;
    }

    public void setSort(SortType sort) {
        this.sort = sort;
    }
}
