package com.daniele.mydatabase.shared.model;

public enum SortType {

    ASCENDING,
    DESCENDING;

    public static boolean isAscending(SortType type) {
        return type == SortType.ASCENDING;
    }

    public static boolean isDescending(SortType type) {
        return type == SortType.DESCENDING;
    }
}
