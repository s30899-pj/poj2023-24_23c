package org.example.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.DiscountCodeException;
import org.example.DiscountCodeExpiredException;
import org.example.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService implements DrinkInterface {


    @Override
    public CupSize chooseSize() {
        return null;
    }

    @Override
    public List<Additions> chooseAdditions() {
        return null;
    }

    @Override
    public double calculatePrice(CupSize cupSize, List<Additions> additions) {
        return 0;
    }
}
