package ru.mironenko.inout;

import java.io.*;

/**
 * Created by nikita on 01.11.2016.
 */
public class EvenNumber {

    /**
     * The method check that number is even
     * @param reader
     * @return result true if number is even false if number is odd
     * @throws IOException
     */
    public static boolean isNumber(BufferedReader reader) throws IOException {
        boolean result = false;
        int num = Integer.parseInt(reader.readLine());

        if ((num % 2) == 0){
            result = true;
        }

        return result;
    }

}
