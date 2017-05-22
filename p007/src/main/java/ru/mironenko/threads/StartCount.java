package ru.mironenko.threads;

import ru.mironenko.threads.CountSpaces;
import ru.mironenko.threads.CountWords;

/**
 * Created by nikita on 19.05.2017.
 */

//Часть 2.
//        Необходимо, что бы вначале запуска программы из первой части на экране выводилась информация о программе.
//        А после вычисления данных, выводилась запись о завершении программы.
//        Важно, эта информация всегда должна выводиться в начале и в конце вычисления.
//        Предусмотреть возможность останавливать вычисления, если время вычисления превышает 1 секунду.
//        Для этого нужно реализовать проверку поведения isInterrupt()



public class StartCount {

    String text;
    CountWords countWords;
    CountSpaces countSpaces;


    public StartCount(String text) {
        this.text = text;

    }

    public void countWordsAndSpaces() {

        this.countWords = new CountWords(this.text);
        this.countSpaces = new CountSpaces(this.text);

        try{
            System.out.println("Program to count words and spaces if started");

            this.countWords.t.join();
            this.countSpaces.t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counting is finished");

    }

    public static void main(String[] args) {

        new StartCount("This text includes five simple spaces").countWordsAndSpaces();
    }
}
