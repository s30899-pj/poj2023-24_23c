package org.example.services;

import org.example.models.Additions;
import org.example.models.CupSize;
import org.example.models.DrinkType;

import java.util.List;

public interface DrinkInterface {
    default double order() {
        return calculatePrice(chooseSize(), chooseAdditions());
    }
    CupSize chooseSize();
    List<Additions> chooseAdditions();
    double calculatePrice(CupSize cupSize, List<Additions> additions);
}
