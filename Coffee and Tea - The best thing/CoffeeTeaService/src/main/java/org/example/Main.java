package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkInterface drinkService = null;
        OrderProcess orderProcess = null;

        System.out.println("Welcome in Coffee and Tea- The best thing!");
        System.out.println("Would you like to order coffee or tea? (Coffee/Tea)");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "coffee":
                drinkService = new CoffeeService();
                orderProcess = (OrderProcess) drinkService;
                drinkService.order();
                orderProcess.saveOrder();
                break;
            case "tea":
                ReduceInterface discountService = new DiscountService();
                drinkService = new TeaService(discountService);
                orderProcess = (OrderProcess) drinkService;
                drinkService.order();
                orderProcess.saveOrder();
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }
    }
}