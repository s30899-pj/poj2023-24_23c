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
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService implements DrinkInterface {
    private OrderService orderService = new OrderService();
    private ReduceInterface discountService = new DiscountService();
    Scanner scanner = new Scanner(System.in);


    @Override
    public CupSize chooseSize() {
        System.out.print("Choose tea size (S, M, L): ");
        try {
            return TeaSizes.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e){
            System.out.println("Invalid size. Size M was selected by default");
        }

        return TeaSizes.M;
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
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public double calculatePrice(CupSize cupSize, List<Additions> additions) {
        double basicPrice = cupSize.getPrice();

        basicPrice += additions.stream()
                .mapToDouble(Additions::getPrice)
                .sum();

        basicPrice *= (1 - getDiscount());

        orderService.saveOrder(additions, basicPrice, cupSize, DrinkType.TEA);

        return basicPrice;
    }

    public double getDiscount() {
        System.out.println("Do you have a discount code? (Yes/No):");

        if (scanner.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("Enter the discount code:");
            String code = scanner.nextLine();
            try {
                return discountService.getDiscount(code);
            } catch (DiscountCodeException | DiscountCodeExpiredException e) {
                System.out.println(e.getMessage());
            }
        }

        return 0;
    }
}