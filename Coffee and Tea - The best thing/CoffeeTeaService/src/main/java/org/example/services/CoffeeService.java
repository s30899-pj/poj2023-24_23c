package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.models.additions.Additions;
import org.example.models.additions.CoffeeAdditions;
import org.example.models.drink.DrinkType;
import org.example.models.sizes.CoffeeSizes;
import org.example.models.sizes.CupSize;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        System.out.println("Choose coffee additions (enter the numbers separated by commas e.g., 1,3):");
        Arrays.stream(CoffeeAdditions.values())
                .forEach(addition -> System.out.println(addition.getNumber() + ". " + addition));

        try {
            String[] choices = scanner.nextLine().split(",");
            return Arrays.stream(choices)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(number -> Arrays.stream(CoffeeAdditions.values())
                            .filter(addition -> addition.getNumber() == number)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid addition number: " + number)))
                    .map(addition -> (Additions) addition)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers separated by commas.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void calculatePrice(CupSize cupSize, List<Additions> additions) {
        double price = Arrays.stream(CoffeeSizes.values())
                .filter(sizeEnum -> sizeEnum == cupSize)
                .findFirst()
                .map(CoffeeSizes::getPrice)
                .orElse(8.0);

        double additionsPrice = additions.stream()
                .mapToDouble(addition -> ((CoffeeAdditions) addition).getPrice())
                .sum();
        price += additionsPrice;

        System.out.println("Total price for coffee: " + String.format("%.2f", price));

        orderService.saveOrder(additions, price, cupSize, DrinkType.COFFEE);

    }

}
