package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.DrinkInterface;
import org.example.additions.CoffeeAdditions;
import org.example.additions.TeaAdditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public abstract class DrinkService implements DrinkInterface {
    double price;
    String size;
    List<TeaAdditions> selectedTeaAdditions;
    List<CoffeeAdditions> selectedCoffeeAdditions;

    @Override
    public void chooseSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose tea size (S, M, L): ");
        size = scanner.nextLine().toUpperCase();
    }

    @Override
    public void chooseAdditions(String choice){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose " + choice.toLowerCase() + " additions (enter the numbers separated by commas e.g., 1,3):");

        switch (choice.toUpperCase()) {
            case "TEA":
                selectedTeaAdditions = new ArrayList<>();
                Arrays.stream(TeaAdditions.values())
                        .forEach(addition -> System.out.println(addition.getNumber() + ". " + addition));
                break;
            case "COFFEE":
                selectedCoffeeAdditions = new ArrayList<>();
                Arrays.stream(CoffeeAdditions.values())
                        .forEach(addition -> System.out.println(addition.getNumber() + ". " + addition));
                break;
            default:
                System.out.println("Invalid choice: " + choice);
                return;
        }

        String input = scanner.nextLine();
        String[] choices = input.split(",");

        for (String userChoice : choices) {
            try {
                int option = Integer.parseInt(userChoice.trim());
                switch (choice.toUpperCase()) {
                    case "TEA":
                        for (TeaAdditions addition : TeaAdditions.values()) {
                            if (addition.getNumber() == option) {
                                selectedTeaAdditions.add(addition);
                            }
                        }
                        break;
                    case "COFFEE":
                        for (CoffeeAdditions addition : CoffeeAdditions.values()) {
                            if (addition.getNumber() == option) {
                                selectedCoffeeAdditions.add(addition);
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid choice: " + choice);
                        return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice: " + userChoice);
            }
        }
    }

    @Override
    public abstract void calculatePrice();

    public abstract void saveOrder();
}
