package org.example.model;

import java.util.Random;

public class CardPayment implements PaymentMethod {

    Random random = new Random();
    int shouldPrint = random.nextInt(2);

    @Override
    public boolean processPayment(double amountPaid, double priceInPLN) {
        if (shouldPrint == 1) {
            System.out.println("Płatność kartą na kwotę: " + priceInPLN + " PLN została przetworzona.");
            return true;
        } else {
            System.out.println("Płatność kartą na kwotę: " + priceInPLN + " PLN nie powiodła się.");
            return false;
        }
    }
}
