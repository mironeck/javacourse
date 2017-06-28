package ru.mironenko.waitnotify.learningcode;

import java.util.LinkedList;

/**
 * Created by nikita on 11.06.2017.
 */
public class ProcessorTwo {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException{

        int value = 0;

        while(true) {
            synchronized (lock) {

                while(list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException{

        while(true) {
            synchronized (lock) {
                while(list.size() == 0) {
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

    public static void main(String[] args) throws InterruptedException{

        final ProcessorTwo proc = new ProcessorTwo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    proc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    proc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(30000);
        System.exit(0);
    }
}
