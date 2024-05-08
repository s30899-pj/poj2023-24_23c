package org.example;

import org.example.model.*;

import java.util.Scanner;

public class MachineSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dostępne produkty:");
        for (Products product : Products.values()) {
            System.out.println(product.getNumber() + ". " + product.name() + " - " + product.getValue() + " " + Currency.PLN.name());
        }
        System.out.print("Wybierz numer produktu: ");
        int productNumber = scanner.nextInt();

        System.out.println("Wybierz metodę płatności: ");
        System.out.println("1. Gotówka");
        System.out.println("2. Karta");
        System.out.print("Wybierz numer metody płatności: ");
        int paymentMethodChoice = scanner.nextInt();

        PaymentMethod paymentMethod;
        double amountPaid = 0;
        Currency currency = Currency.PLN;
        if (paymentMethodChoice == 1) {
            System.out.print("Podaj kwotę: ");
            amountPaid = scanner.nextDouble();

            System.out.print("Podaj walutę (PLN/EUR/USD/GBP): ");
            currency = Currency.valueOf(scanner.next());

            paymentMethod = new CashPayment();
        } else if (paymentMethodChoice == 2) {
            paymentMethod = new CardPayment();
        } else {
            System.out.println("Nieprawidłowy numer metody płatności. Wybierz 1 lub 2.");
            return;
        }

        selectProduct(productNumber, amountPaid, currency, paymentMethod);
    }

    public static void selectProduct(int productNumber, double amountPaid, Currency currency, PaymentMethod paymentMethod) {
        try {
            Products selectedProduct = Products.getProductByNumber(productNumber);
            double productPrice = selectedProduct.getValue();
            double amountInPLN = 0;
            if (paymentMethod instanceof CashPayment) {
                amountInPLN = amountPaid * currency.getEquivalent();
            } else if (paymentMethod instanceof CardPayment) {
                amountInPLN = productPrice;
                amountPaid = productPrice;
            }

            if (amountInPLN >= productPrice) {
                boolean paymentStatus = paymentMethod.processPayment(amountInPLN, productPrice);
                if (paymentStatus) {
                    System.out.println("Transakcja zakończona pomyślnie.");
                    System.out.println("Wybrano produkt: " + selectedProduct.name());
                    System.out.println("Zapłacono: " + amountPaid + " " + currency.name());
                    System.out.println("Cena produktu: " + productPrice + " PLN");
                    System.out.println("Odbierz produkt: " + selectedProduct.name());
                } else {
                    System.out.println("Transakcja nieudana. Spróbuj ponownie lub wybierz inną metodę płatności.");
                }
            } else {
                System.out.println("Niewystarczająca ilość pieniędzy.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowy numer produktu.");
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}