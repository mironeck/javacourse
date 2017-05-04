package ru.mironenko.collectionspro.controltasks.orderbook;

/**
 * Created by nikita on 19.04.2017.
 */
public class Order implements Comparable<Order>{

    private final String book;
    private final int id;
    private final String operation;
    private final Double price;
    private final int volume;

    public Order(String book, String operation, double price, int volume, int id){
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }


    public String getBook() {
        return book;
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

    @Override
    public int compareTo(Order o) {
        Order tmp = (Order)o;
        if(this.id < tmp.id) {
            return -1;
        } else if(this.id > tmp.id) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (id != order.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
//        int result;
//        long temp;
//        result = getId();
//        result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
//        temp = Double.doubleToLongBits(getPrice());
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + getVolume();
        return id;
    }
}
