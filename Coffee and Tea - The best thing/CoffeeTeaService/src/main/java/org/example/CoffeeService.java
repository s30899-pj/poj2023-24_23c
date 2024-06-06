package org.example;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@Getter
@Setter
public class CoffeeService implements DrinkInterface, OrderProcess {
    private String size;
    private boolean sugar;
    private boolean milk;
    private boolean cinnamon;
    private boolean ginger;
    private double price;


    @Override
    public void chooseSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose coffee size (S, M, L):");
        size = scanner.nextLine();
    }

    @Override
    public void chooseAdditions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose tea additions (entering the number seperated by commas e.g. 1,3):");
        System.out.println("1. Sugar");
        System.out.println("2. Milk");
        System.out.println("3. Cinnamon");
        System.out.println("4. Ginger");

        String input = scanner.nextLine();
        String[] choices = input.split(",");

        for (String choice : choices) {
            switch (choice) {
                case "1":
                    sugar = true;
                    break;
                case "2":
                    milk = true;
                    break;
                case "3":
                    cinnamon = true;
                    break;
                case "4":
                    ginger = true;
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
                price = 6.0;
                break;
            case "M":
                price = 8.0;
                break;
            case "L":
                price = 10.0;
                break;
            default:
                System.out.println("Invalid size. Defaulting to size M.");
                price = 8.0;
        }

        if (sugar) {
            price += 0.3;
        }
        if (milk) {
            price += 0.4;
        }
        if (cinnamon) {
            price += 0.7;
        }
        if (ginger) {
            price += 0.8;
        }

        System.out.println("Total price for coffee:" + String.format("%.2f", price));
    }

    @Override
    public void saveOrder() {
        try (FileWriter fw = new FileWriter("Orders.txt", true);
             PrintWriter pw = new PrintWriter(fw)){
            pw.printf("Order: Coffee | Size: %s | Sugar: %s | Milk: %s | Cinnamon: %s | Ginger: %s | Final Price: %.2f\n",
                    size, sugar, milk, cinnamon, ginger, price);
        } catch (IOException e){
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}
