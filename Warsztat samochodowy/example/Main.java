package org.example;

public class Main {
    public static void main(String[] args) {
        CarFixService carfixservice = new CarFixService();

        carfixservice.addCar(new Car("BMW", new Body("LIMOUSINE", colors.RED), new Engine("N46", 2.0),new Wheels(180)));
        carfixservice.addCar(new Car("TOYOTA", new Body("HATCHBACK", colors.BLACK), new Engine("1CD", 2.0),new Wheels(160)));
        carfixservice.addCar(new Car("AUDI",new Body("ESTATE", colors.WHITE),new Engine("BEX", 2.0),new Wheels(170)));

        System.out.println("Cars in car fix service:");
        for(Car car : carfixservice.getCars()) {
            System.out.println(car);
        }

        System.out.println("\nRepaired cars (foreach):");
        for(Car car : carfixservice.cars) {
            Car repiredCar = carfixservice.repairCar(car,Commo.WHEELS_FAILURE,new Body("LIMOUSINE",colors.BLACK),new Engine("M46", 2.5),new Wheels(200));
            System.out.println(repiredCar);
        }

        System.out.println("\nRepaired cars (for): ");
        for (int i = 0; i < carfixservice.cars.size(); i++){
            Car repiredCar = carfixservice.repairCar(carfixservice.getCars().get(i), Commo.WHEELS_FAILURE, new Body("LIMOUSINE", colors.BLACK), new Engine("M46", 2.5), new Wheels(200));
            System.out.println(repiredCar);
        }

        System.out.println("\nRepaired cars (stream): ");
        carfixservice.getCars().forEach(car -> {
            carfixservice.repairCar(car, Commo.WHEELS_FAILURE, new Body("LIMOUSINE", colors.BLACK), new Engine( "M46", 2.5), new Wheels(200));
            System.out.println(car);
        });
    }
}