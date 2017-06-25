package ru.mironenko.threads;

/**
 * Created by nikita on 25.06.2017.
 */
public class TaskInterrupt extends Thread {

    public void run(){

        try {
            for(int i = 0; i < 10; i++) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.printf("Thread %s is interrupted \n", Thread.currentThread().getName() );
                } else {
                    Thread.sleep(1000);
                    System.out.println("Task");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exception handled " + e);
        }
        System.out.println("Thread running");
    }

    public static void main(String[] args) throws InterruptedException {

        TaskInterrupt taskInterrupt = new TaskInterrupt();
        taskInterrupt.start();
        Thread.sleep(5000);
        taskInterrupt.interrupt();

    }
}
