package org.example;

import lombok.*;

import java.util.*;

@Getter
public class CarFixService {

    List<Car> cars;

    public CarFixService() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Car repairCar(Car car, Commo commo, Body newBody, Engine newEngine, Wheels newWheels) {
        switch (commo) {
            case BODY_FAILURE:
                replaceBody(car, newBody);
                break;
            case ENGINE_FAILURE:
                replaceEngine(car, newEngine);
                break;
            case WHEELS_FAILURE:
                replaceWheels(car, newWheels);
                break;
            default:
                System.out.println("Unknown failure type");
        }
        return car;
    }

    private void replaceBody(Car car, Body newBody) {
        car.setBody(newBody);
    }

    private void replaceEngine(Car car, Engine newEngine) {
        car.setEngine(newEngine);
    }

    private void replaceWheels(Car car, Wheels newWheels) {
        car.setWheels(newWheels);
    }
}

