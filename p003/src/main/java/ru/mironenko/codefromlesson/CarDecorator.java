package ru.mironenko.codefromlesson;

/**
 * Created by nikita on 01.11.2016.
 */
public abstract class CarDecorator implements Car {

    protected final Car car;

    public CarDecorator(Car car){
        this.car = car;
    }
    public int getPrice(){
        return car.getPrice();
    }

    public String getDescription(){
        return car.getDescription();
    }

}
