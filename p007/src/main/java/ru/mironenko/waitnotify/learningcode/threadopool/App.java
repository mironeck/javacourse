package ru.mironenko.waitnotify.learningcode.threadopool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by nikita on 11.06.2017.
 */
public class App {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i++){
            executor.submit(new Processor(i));
        }

        executor.shutdown();
        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");
    }
}
