package org.example.models.drink;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.additions.Additions;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Drink {
    private double price;
    private String size;
    private List<Additions> additions;

}
