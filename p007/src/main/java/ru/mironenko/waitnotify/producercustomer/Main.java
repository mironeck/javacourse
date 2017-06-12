package ru.mironenko.waitnotify;

import java.util.LinkedList;

/**
 * Created by nikita on 11.06.2017.
 */
public class Main {

    public static LinkedList<Integer> list = new LinkedList<>();
    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Producer(Main.list, Main.lock));
        Thread t2 = new Thread(new Customer(Main.list, Main.lock));

        t1.start();
        t2.start();

        Thread.sleep(30000);
        System.exit(0);
    }
}
