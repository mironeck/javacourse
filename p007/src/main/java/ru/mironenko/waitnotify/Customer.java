package ru.mironenko.waitnotify;

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
    private Object lock;

    /**
     * Constructor of the class.
     * @param list - store of elements
     * @param lock - object lock for thread
     */
    public Customer(final LinkedList list, Object lock) {
        this.lock = lock;
        this.list = list;
    }


    /**
     * Removes elements frome list. If list size equals zero waits while producer adds elements to it.
     * Removes elements then wakes up thread that wait on this object monitor.
     * @throws InterruptedException
     */
    private void consume() throws InterruptedException {

        while(true) {
            synchronized (lock){
                while(list.size() == 0){
                    lock.wait();
                }
                System.out.print("List size is " + list.size());
                int value = list.removeFirst();
                System.out.println(" ; value is " + value);
                lock.notify();
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
