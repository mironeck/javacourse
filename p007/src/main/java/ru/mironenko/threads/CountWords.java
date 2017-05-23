package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */

//Часть 1.
//        Создать программу, которая будет считать количество слов и пробелов в тексте.
//        Необходимо создать два Thread. Первый будет считать количество слов. Второй количество пробелов в тексте.
//        Вывести результат на экран.
//        Продемонстрировать, что программа выполняется параллельно. Это будет видно по выводу.

import java.io.*;
import java.util.Scanner;

/**
 * Class CountWords has a test as a parameter of the constructor and counts words in the text
 */
public class CountWords implements Runnable {

    private String text;
    Thread t;

    /**
     * Constructor of CountWords
     * @param text
     */
    public CountWords(String text) {
        this.text = text;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        String [] words = this.text.split(" +");
        int wordsCount = words.length;
        System.out.format("Number of words in the text is %s . \n", wordsCount);

    }

}
