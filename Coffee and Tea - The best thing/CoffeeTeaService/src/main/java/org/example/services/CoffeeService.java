package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.additions.CoffeeAdditions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
@Setter
public class CoffeeService extends DrinkService {
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

        double additionsPrice = selectedCoffeeAdditions.stream()
                .mapToDouble(CoffeeAdditions::getPrice)
                .sum();
        price += additionsPrice;

        System.out.println("Total price for coffee: " + String.format("%.2f", price));
    }

    @Override
    public void saveOrder() {
        try (FileWriter fw = new FileWriter("Orders.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Order: Coffee | Size: %s | Additions: %s | Final Price: %.2f\n",
                    size, selectedCoffeeAdditions, price);
        } catch (IOException e) {
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}
