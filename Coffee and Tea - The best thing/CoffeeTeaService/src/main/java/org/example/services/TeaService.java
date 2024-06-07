package org.example.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.DiscountCodeException;
import org.example.DiscountCodeExpiredException;
import org.example.ReduceInterface;
import org.example.additions.TeaAdditions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService extends DrinkService {
    private double discount = 0.0;
    @NonNull
    private ReduceInterface discountService;

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

        double additionsPrice = selectedTeaAdditions.stream()
                .mapToDouble(TeaAdditions::getPrice)
                .sum();
        price += additionsPrice;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have a discount code? (Yes/No):");
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
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Order: Tea | Size: %s | Additions: %s | Discount: %.2f | Final Price: %.2f\n",
                    size, selectedTeaAdditions, discount, price);
        } catch (IOException e) {
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}
