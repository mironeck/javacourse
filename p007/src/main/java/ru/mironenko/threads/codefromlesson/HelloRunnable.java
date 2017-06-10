package ru.mironenko.threads.codefromlesson;

/**
 * Created by nikita on 19.05.2017.
 */
public class HelloRunnable implements Runnable {

    private String name;

    public HelloRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(String.format("Hello from %s", this.name));
    }

    public static void main(String[] args) {

        new Thread(new HelloRunnable("thread 1")).start();
        new Thread(new HelloRunnable("thread 2")).start();
    }
}
