package org.example.models.additions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffeeAdditions implements Additions {
    SUGAR(1,0.3),
    MILK(2,0.4),
    CINNAMON(3,0.7),
    GINGER(4,0.8);

    private final int number;
    private final double price;
}
