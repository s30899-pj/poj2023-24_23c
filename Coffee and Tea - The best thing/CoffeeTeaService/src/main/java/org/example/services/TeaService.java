package org.example.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.DiscountCodeException;
import org.example.DiscountCodeExpiredException;
import org.example.DiscountService;
import org.example.models.additions.Additions;
import org.example.models.additions.TeaAdditions;
import org.example.models.drink.DrinkType;
import org.example.models.sizes.CupSize;
import org.example.models.sizes.TeaSizes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService implements DrinkInterface {
    private OrderService orderService = new OrderService();
    private ReduceInterface discountService = new DiscountService();
    private double discount = 0.0;
    Scanner scanner = new Scanner(System.in);


    @Override
    public CupSize chooseSize() {
        System.out.print("Choose tea size (S, M, L): ");
        return TeaSizes.valueOf(scanner.nextLine().toUpperCase());
    }

    @Override
    public List<Additions> chooseAdditions() {
        System.out.println("Choose coffee additions (enter the numbers separated by commas e.g., 1,3):");
        Arrays.stream(TeaAdditions.values())
                .forEach(addition -> System.out.println(addition.getNumber() + ". " + addition));

        try {
            String[] choices = scanner.nextLine().split(",");
            return Arrays.stream(choices)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(number -> Arrays.stream(TeaAdditions.values())
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
        double price = Arrays.stream(TeaSizes.values())
                .filter(sizeEnum -> sizeEnum == cupSize)
                .findFirst()
                .map(TeaSizes::getPrice)
                .orElse(8.0);

        double additionsPrice = additions.stream()
                .mapToDouble(addition -> ((TeaAdditions) addition).getPrice())
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

        orderService.saveOrder(additions, price, cupSize, DrinkType.TEA);

    }
}