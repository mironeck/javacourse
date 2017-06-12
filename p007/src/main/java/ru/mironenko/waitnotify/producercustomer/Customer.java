package ru.mironenko.waitnotify.producercustomer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikita on 10.06.2017.
 */

/**
 * Customer class. Removes elements from list.
 */
public class Customer implements Runnable {

    /**
     * Store of elements
     */
    private LinkedList<Integer> list;
    /**
     * Object lock for thread
     */

    /**
     * Constructor of the class.
     * @param list - store of elements
     */
    public Customer(final LinkedList list) {
        this.list = list;
    }


    /**
     * Removes elements frome list. If list size equals zero waits while producer adds elements to it.
     * Removes elements then wakes up thread that wait on this object monitor.
     * @throws InterruptedException
     */
    private void consume() throws InterruptedException {

        while(true) {
            synchronized (list){
                while(list.size() == 0){
                    list.wait();
                }
                System.out.print("List size is " + list.size());
                int value = list.removeFirst();
                System.out.println(" ; value is " + value);
                list.notify();
            }
            Thread.sleep(1000);
        }
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
