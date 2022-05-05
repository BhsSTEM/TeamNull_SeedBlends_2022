package com.example.teamnull_seedblends_2022;

public class FieldItem {
    private String name;
    private String coords;

    public FieldItem(String n, String c) {
        name = n;
        coords = c;
    }

    public String getName() {
        return name;
    }

    public String getCoords() {
        return coords;
    }

    public String toString() {
        return name;
    }
}
