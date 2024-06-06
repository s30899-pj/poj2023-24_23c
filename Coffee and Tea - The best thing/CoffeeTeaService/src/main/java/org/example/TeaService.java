package org.example;

import lombok.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService implements DrinkInterface, OrderProcess {
    private String size;
    private boolean sugar;
    private boolean honey;
    private boolean lemon;
    private boolean syrup;
    private double price;
    private double discount = 0.0;
    @NonNull
    private ReduceInterface discountService;

    @Override
    public void chooseSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose tea size (S, M, L): ");
        size = scanner.nextLine().toUpperCase();
    }

    @Override
    public void chooseAdditions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose tea additions (entering the number seperated by commas e.g. 1,3):");
        System.out.println("1. Sugar");
        System.out.println("2. Honey");
        System.out.println("3. Lemon");
        System.out.println("4. Syrup");

        String input = scanner.nextLine();
        String[] choices = input.split(",");

       for (String choice : choices) {
            switch (choice) {
                case "1":
                    sugar = true;
                    break;
                case "2":
                    honey = true;
                    break;
                case "3":
                    lemon = true;
                    break;
                case "4":
                    syrup = true;
                    break;
                default:
                    System.out.println("Invalid choice" + choice);
            }
        }
    }

    @Override
    public void calculatePrice() {
        switch (size) {
            case "S":
                price = 5.0;
                break;
            case "M":
                price = 7.0;
                break;
            case "L":
                price = 9.0;
                break;
            default:
                System.out.println("Invalid size. Defaulting to size M.");
                price = 7.0;
        }

        if (sugar) {
            price += 0.3;
        }
        if (honey) {
            price += 0.5;
        }
        if (lemon) {
            price += 0.8;
        }
        if (syrup) {
            price += 0.9;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have discount code? (Yes/No):");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Yes")) {
            System.out.println("Enter the discount code:");
            String code = scanner.nextLine();
            try {
                discount = discountService.getDiscount(code);
                System.out.println("Discount code successfully added.");
            } catch (DiscountCodeException | DiscountCodeExpiredException e) {
                System.out.println(e.getMessage());
            }
        }

        price *= (1 - discount);
        System.out.println("Total price for tea: " + String.format("%.2f", price));

    }

    @Override
    public void saveOrder() {
        try (FileWriter fw = new FileWriter("Orders.txt", true);
             PrintWriter pw = new PrintWriter(fw)){
            pw.printf("Order: Tea | Size: %s | Sugar: %s | Honey: %s | Lemon: %s | Syrup: %s | Discount: %.2f | Final Price: %.2f\n",
                    size, sugar, honey, lemon, syrup, discount, price);
        } catch (IOException e){
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}


