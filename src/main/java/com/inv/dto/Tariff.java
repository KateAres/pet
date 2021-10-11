package com.inv.dto;

import lombok.Getter;

public enum Tariff {
    ESSENTIALS(3, "Essentials"),
    PREMIUM(2, "Premium"),
    ENTERPRISE(1, "Enterprise");

    @Getter
    private int priority;
    @Getter
    private String name;

    Tariff(int priority, String name) {
        this.name = name;
        this.priority = priority;
    }
}
