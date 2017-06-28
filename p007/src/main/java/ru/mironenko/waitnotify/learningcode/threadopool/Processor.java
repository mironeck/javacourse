package ru.mironenko.waitnotify.learningcode.threadopool;

/**
 * Created by nikita on 11.06.2017.
 */
public class Processor implements Runnable{

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting id " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed id " + id);
    }
}
