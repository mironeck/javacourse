package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */


/**
 * Class CountSpaces has a test as a parameter of the constructor and counts spaces in the text
 */
public class CountSpaces implements Runnable {

    String text;
    Thread t;

    /**
     * CountSpaces constructor
     * @param text
     */
    public CountSpaces(String text) {
        this.text = text;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        String [] words = this.text.split(" +");
        int spacesCount = words.length;
        System.out.format("Number of spaces in the text is %s . \n", spacesCount - 1);

    }
}
