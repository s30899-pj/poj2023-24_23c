package org.example;

import lombok.*;

@Setter
@AllArgsConstructor
public class Car {

    private String model;

    private Body body;

    private Engine engine;

    private Wheels wheels;

    @Override
    public String toString() {
        return "Car: " +
                "model-" + model + " " +
                 body + " " +
                 engine + " " +
                 wheels;
    }
}