package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */


/**
 * Class CountSpaces has a test as a parameter of the constructor and counts spaces in the text
 */
public class CountSpaces implements Runnable {

    String text;

    /**
     * CountSpaces constructor
     * @param text
     */
    public CountSpaces(String text) {
        this.text = text;
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
