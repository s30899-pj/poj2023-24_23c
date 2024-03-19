package org.example;

import lombok.*;

@Setter
@AllArgsConstructor
public class Engine {

    private String name;

    private double capacity;

    @Override
    public String toString() {
        return "Engine: " +
                "name-" + name +
                ", capacity-" + capacity;
    }
}
