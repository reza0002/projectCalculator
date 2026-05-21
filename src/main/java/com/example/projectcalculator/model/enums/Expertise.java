package com.example.projectcalculator.model.enums;

public enum Expertise {
    JUNIOR(1000),
    MID(1200),
    SENIOR(1400);

    private final int price;

    Expertise(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
