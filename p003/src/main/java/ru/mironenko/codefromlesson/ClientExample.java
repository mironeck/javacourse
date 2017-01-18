package ru.mironenko.codefromlesson;

/**
 * Created by nikita on 01.11.2016.
 */
public class ClientExample {

    public static void main(String[] args) {

        Car car = new SimpleCar();
        System.out.println(car.getPrice() + " " + car.getDescription());

        Car conditionCar = new Conditioner(new SimpleCar());
        System.out.println(conditionCar.getPrice() + " " + conditionCar.getDescription());

        Car woodenCar = new WoodInterior(new Conditioner(new SimpleCar()));
        System.out.println(woodenCar.getPrice() + " " + woodenCar.getDescription());

        Car sccar = new WoodInterior(new SimpleCar());
        System.out.println(sccar.getPrice() + " " + sccar.getDescription());

        String data = "Hello, World!\r\n";
        System.out.println(data);
    }
}
