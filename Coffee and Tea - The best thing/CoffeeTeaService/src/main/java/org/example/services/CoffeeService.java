package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class CoffeeService implements DrinkInterface {
    private OrderService orderService = new OrderService();
    Scanner scanner = new Scanner(System.in);

    @Override
    public CupSize chooseSize() {
        System.out.print("Choose tea size (S, M, L): ");
        return CoffeeSizes.valueOf(scanner.nextLine().toUpperCase());
    }


    @Override
    public List<Additions> chooseAdditions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose coffee additions (enter the numbers separated by commas e.g., 1,3):");
        System.out.println(Arrays.stream(CoffeeAdditions.values()));

        String[] choices = scanner.nextLine().split(",");

        return Arrays.stream(choices).map(CoffeeAdditions::valueOf).toList();
    }

    @Override
    public double calculatePrice(CupSize cupSize, List<Additions> additions) {
        double price = Arrays.stream(CoffeeSizes.values())
                .filter(sizeEnum -> sizeEnum.name().equals(size))
                .findFirst()
                .map(CoffeeSizes::getPrice)
                .orElse(7.0);

        double additionsPrice = selectedCoffeeAdditions.stream()
                .mapToDouble(CoffeeAdditions::getPrice)
                .sum();
        price += additionsPrice;

        System.out.println("Total price for coffee: " + String.format("%.2f", price));

        orderService.saveOrder(price, cupSize, additionsPrice);

        return additionsPrice;
    }
}
