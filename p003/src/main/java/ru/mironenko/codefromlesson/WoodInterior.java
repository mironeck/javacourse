package ru.mironenko.codefromlesson;

/**
 * Created by nikita on 01.11.2016.
 */
public class WoodInterior extends CarDecorator {

    public WoodInterior(Car car){
        super(car);
    }

    public int getPrice(){
        return car.getPrice() + 30000;
    }

    public String getDescription(){
        return car.getDescription() + " торпедо с деревянными вставками";
    }

}
