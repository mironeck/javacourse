package ru.mironenko.threads.codefromlesson;

/**
 * Created by nikita on 22.05.2017.
 */
public class OracleTests {

    public static void main(String[] args) {


        String[] importantInfo = new String[]{"1","2","3","4"};

        for (int i = 0; i < importantInfo.length; i++) {
            // Pause for 4 seconds
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // We've been interrupted: no more messages.
                return;
            }
            // Print a message
            System.out.println(importantInfo[i]);
        }
    }
}
