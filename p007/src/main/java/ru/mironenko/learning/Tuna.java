package ru.mironenko.learning;

import java.util.Random;

/**
 * Created by nikita on 16.06.2017.
 */
public class Tuna implements Runnable{

    private String name;
    Random r = new Random();
    int time;

    public Tuna(String name){
        this.name = name;
        time = r.nextInt(999);
    }

    @Override
    public void run() {

        System.out.printf("%s is running\n", name);
        //Thread.yield();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s finished \n", name);
    }


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Tuna("one"));
        Thread t2 = new Thread(new Tuna("two"));
        Thread t3 = new Thread(new Tuna("three"));
        Thread t4 = new Thread(new Tuna("four"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
