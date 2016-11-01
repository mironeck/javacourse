package ru.mironenko.codefromlesson;

/**
 * Created by nikita on 01.11.2016.
 */
public class Conditioner extends CarDecorator {

    public Conditioner(Car car){
        super(car);
    }

    public int getPrice(){
        return car.getPrice() + 20000;
    }

    public String getDescription(){
        return car.getDescription() + " с кондицонером";
    }
}
