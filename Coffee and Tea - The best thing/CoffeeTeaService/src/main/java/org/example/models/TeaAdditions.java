package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeaAdditions implements Additions {
    SUGAR(1,0.3),
    HONEY(2,0.5),
    LEMON(3,0.8),
    SYRUP(4,0.9);

    private final int number;
    private final double price;
}
