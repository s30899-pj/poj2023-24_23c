package org.example;

import lombok.NonNull;
import org.example.services.CoffeeService;
import org.example.services.DrinkInterface;
import org.example.models.drink.DrinkType;
import org.example.services.TeaService;

import java.util.Scanner;

public class Main {
    private static DrinkInterface drinkService;

    public static void main(String[] args) {
        switch (chooseDrinkType()) {
            case COFFEE:
                drinkService = new CoffeeService();
                break;
            case TEA:
                drinkService = new TeaService();
                break;
        }

        double price = drinkService.order();

        System.out.println("Total price for drink: " + String.format("%.2f", price));
    }

    @NonNull
    public static DrinkType chooseDrinkType(){
        System.out.println("Welcome to Coffee and Tea - The Best Thing!");
        System.out.println("Would you like to order coffee or tea? (COFFEE/TEA)");

        try {
            return DrinkType.valueOf(new Scanner(System.in).nextLine().toUpperCase());
        } catch (IllegalArgumentException e){
            System.out.println("Invalid drink type.");
            throw e;
        }
    }
}