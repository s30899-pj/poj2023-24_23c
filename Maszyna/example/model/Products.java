package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Products {
    CHIPS(1, 4.5),
    CHOCOLATE(2, 3),
    CHOCOLATE_BAR(3,2.5),
    GUMMY_CANDIES(4,1.5),
    SWEET_ROLL(5,2),
    WATER(6,1),
    FIZZY_DRINK(7,3),
    ENERGY_DRINK(8,3.5);

    private final int number;
    private final double value;

    public static Products getProductByNumber(int number) {
        for (Products product : Products.values()) {
            if (product.getNumber() == number) {
                return product;
            }
        }
        throw new IllegalArgumentException("Nie ma produktu o podanym numerze: " + number);
    }
}
