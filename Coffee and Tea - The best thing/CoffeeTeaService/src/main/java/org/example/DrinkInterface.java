package org.example;

public interface DrinkInterface {
     default void order(){
        chooseSize();
        chooseAdditions();
        calculatePrice();
    }
    void chooseSize();
    void chooseAdditions();
    void calculatePrice();
}
