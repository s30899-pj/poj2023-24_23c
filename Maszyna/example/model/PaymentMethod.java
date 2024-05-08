package org.example.model;

public interface PaymentMethod {
    boolean processPayment(double amount, double priceInPLN);
}