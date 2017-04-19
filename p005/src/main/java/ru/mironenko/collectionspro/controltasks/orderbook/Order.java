package ru.mironenko.collectionspro.controltasks.orderbook;

/**
 * Created by nikita on 19.04.2017.
 */
public class Order {

    private final int id;
    private final String operation;
    private final double price;
    private final int volume;


    public Order(int id, String operation, double price, int volume) {
        this.id = id;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }
}
