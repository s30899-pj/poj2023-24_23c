package org.example;

import lombok.NonNull;
import org.example.services.CoffeeService;
import org.example.services.DrinkInterface;
import org.example.models.DrinkType;
import org.example.services.OrderService;
import org.example.services.TeaService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static DrinkInterface drinkService;
    private static OrderService service;


    public static void main(String[] args) {
        switch (chooseDrinkType()) {
            case COFFEE:
                drinkService = new CoffeeService();
                break;
            case TEA:
                drinkService = new TeaService();
                break;
        }

        System.out.println("ZAPŁAĆ BO CIĘ ZNAJDĘ");
        System.out.println(drinkService.order());
    }

    @NonNull
    public static DrinkType chooseDrinkType(){
        System.out.println("Welcome to Coffee and Tea - The Best Thing!");
        System.out.println("Would you like to order coffee or tea? (COFFEE/TEA)");

        try {
            return DrinkType.valueOf(scanner.nextLine().toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("Invalid size.");
            throw e;
        }
    }
}