package ru.mironenko.waitnotify.producercustomer;

import java.util.LinkedList;

/**
 * Created by nikita on 11.06.2017.
 */
//1. Реализовать шаблон Producer Customer.
//Это структура данных на основании блокирующей очереди.
//Producer - добавляет данные, а Customer их забирает. если данных нет,
// то блокировать тред до появления новых данных.

public class Main {

    public static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Producer(Main.list));
        Thread t2 = new Thread(new Customer(Main.list));

        t1.start();
        t2.start();

        Thread.sleep(30000);
        System.exit(0);
    }
}
