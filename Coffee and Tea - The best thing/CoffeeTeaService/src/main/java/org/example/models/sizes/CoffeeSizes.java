package org.example.models.sizes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffeeSizes implements CupSize {
    S("S",6.0),
    M("M",8.0),
    L("L",10.0);

    private final String name;
    private final double price;

}
