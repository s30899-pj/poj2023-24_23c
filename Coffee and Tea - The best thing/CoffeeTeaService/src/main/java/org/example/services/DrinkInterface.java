package org.example.services;

import org.example.models.additions.Additions;
import org.example.models.sizes.CupSize;

import java.util.List;

public interface DrinkInterface {
    default double order() {
         return calculatePrice(chooseSize(), chooseAdditions());
    }
    CupSize chooseSize();
    List<Additions> chooseAdditions();
    double calculatePrice(CupSize cupSize, List<Additions> additions);
}
