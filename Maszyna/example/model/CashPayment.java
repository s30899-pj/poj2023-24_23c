package org.example.model;

public class CashPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amountPaid, double priceInPLN) {
        if (amountPaid >= priceInPLN) {
            double change = amountPaid - priceInPLN;
            if (change > 0) {
                System.out.println("Zapłacono gotówką: " + amountPaid + " PLN. Reszta: " + change + " PLN");
            } else {
                System.out.println("Zapłacono gotówką: " + amountPaid + " PLN.");
            }
            return true;
        } else {
            System.out.println("Niewystarczająca ilość pieniędzy.");
            return false;
        }
    }
}
