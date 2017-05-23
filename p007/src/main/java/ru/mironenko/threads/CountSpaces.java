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

        int spaceCount = 0;

        int endOfLine = this.text.length() - 1;

        for(int i = 0; i < this.text.length(); i++) {

            if(this.text.charAt(i) == ' ') {
                spaceCount++;
                while(this.text.charAt(i) == ' ' && i < endOfLine) {
                    i++;
                }
            }

        }
        System.out.format("Number of spaces in the text is %s . \n", spaceCount);

    }
}
