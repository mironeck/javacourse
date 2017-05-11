package ru.mironenko.collectionspro.controltasks.orderbook;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nikita on 11.05.2017.
 */
public class Book {

    private TreeSet<Order> buy;
    private TreeSet<Order> sell;

    public Book(){
        this.buy = new TreeSet<>();
        this.sell = new TreeSet<>();
    }

    public Set<Order> getBuy() {
        return this.buy;
    }

    public Set<Order> getSell() {
        return this.sell;
    }
}
