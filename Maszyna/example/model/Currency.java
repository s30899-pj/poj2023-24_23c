package org.example.model;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Currency {
    PLN(1),
    EUR(4.5),
    USD(4),
    GBP(5);

    private final double equivalent;
}
