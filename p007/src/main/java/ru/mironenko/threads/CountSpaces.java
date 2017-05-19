package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */
public class CountSpaces implements Runnable {

    String text;

    public CountSpaces(String fileName) {
        this.text = fileName;
    }

    @Override
    public void run() {

        String [] words = text.split(" ");
        System.out.println(String.format("The text includes %s spaces",words.length - 1));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
