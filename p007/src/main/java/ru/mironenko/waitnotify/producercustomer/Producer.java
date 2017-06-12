package ru.mironenko.waitnotify.producercustomer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikita on 10.06.2017.
 */

/**
 * Producer Class. Adds elements in list
 */
public class Producer implements Runnable{

    /**
     * List for store numbers
     */
    private LinkedList<Integer> list;
    /**
     * Limit of elements in list
     */
    private final int LIMIT = 10;

    /**
     * Constructor of the class
     * @param list - store of elements
     */
    public Producer(final LinkedList list){
        this.list = list;
    }

    /**
     * Adds value to the list. If list size is over or equals LIMIT wait until
     * customer remove elements from list. Then adds element to list.
     * @throws InterruptedException
     */
    private void produce() throws InterruptedException {

        int value = 0;

        while(true) {
            synchronized (list){
                while(list.size() == LIMIT) {
                    list.wait();
                }
                list.add(value++);
                list.notify();
            }
        }
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
