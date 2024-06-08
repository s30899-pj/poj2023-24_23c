package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrinkType {
    COFFEE("coffee"),
    TEA("tea");

    private final String name;
}
