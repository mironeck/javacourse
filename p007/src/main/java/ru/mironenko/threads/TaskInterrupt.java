package ru.mironenko.threads;

/**
 * Created by nikita on 25.06.2017.
 */
public class TaskInterrupt extends Thread {

    public void run(){

        try {
            Thread.sleep(1000);
            System.out.println("Task");
        } catch (InterruptedException e) {
            System.out.println("Exception handled " + e);
        }
        System.out.println("Thread running");
    }

    public static void main(String[] args) {

        TaskInterrupt taskInterrupt = new TaskInterrupt();
        taskInterrupt.start();

        taskInterrupt.interrupt();

    }
}
