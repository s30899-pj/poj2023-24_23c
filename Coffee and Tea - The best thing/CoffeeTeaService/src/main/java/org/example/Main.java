package org.example;

import org.example.services.CoffeeService;
import org.example.services.DrinkService;
import org.example.services.TeaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkService drinkService = null;

        System.out.println("Welcome to Coffee and Tea - The Best Thing!");
        System.out.println("Would you like to order coffee or tea? (COFFEE/TEA)");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "coffee":
                drinkService = new CoffeeService();
                drinkService.order(choice);
                drinkService.saveOrder();
                break;
            case "tea":
                ReduceInterface discountService = new DiscountService();
                drinkService = new TeaService(discountService);
                drinkService.order(choice);
                drinkService.saveOrder();
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }
    }
}