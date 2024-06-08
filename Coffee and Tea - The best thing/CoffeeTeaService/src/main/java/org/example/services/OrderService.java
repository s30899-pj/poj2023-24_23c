package org.example.services;

import org.example.models.Additions;
import org.example.models.CupSize;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderService {
    public void saveOrder(List<Additions> selectedAdditions, double price, CupSize size) {
        try (FileWriter fw = new FileWriter("Orders.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Order: Coffee | Size: %s | Additions: %s | Final Price: %.2f\n",
                    size, selectedAdditions, price);
        } catch (IOException e) {
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}
