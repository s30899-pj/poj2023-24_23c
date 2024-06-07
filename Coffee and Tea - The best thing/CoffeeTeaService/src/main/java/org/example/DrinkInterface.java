package org.example;

public interface DrinkInterface {
    default void order(String choice) {
        chooseSize();
        chooseAdditions(choice);
        calculatePrice();
    }
    void chooseSize();
    void chooseAdditions(String choice);
    void calculatePrice();
}
