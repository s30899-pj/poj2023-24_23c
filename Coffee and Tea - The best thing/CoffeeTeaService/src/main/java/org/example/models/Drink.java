package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.Additions;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Drink {
    private double price;
    private String size;
    private List<Additions> additions;

}
