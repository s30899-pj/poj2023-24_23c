package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeaSizes implements CupSize {
    S("S",5.0),
    M("M",7.0),
    L("L",9.0);

    private final String name;
    private final double price;

}
