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



public class StartCount{

    String text;
    CountWords countWords;
    CountSpaces countSpaces;



    public StartCount(String text) {
        this.text = text;
    }


    public void count() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        this.countWords = new CountWords(this.text);
        this.countSpaces = new CountSpaces(this.text);

        System.out.println("Program to count words and spaces if started");

        while(this.countWords.t.isAlive() && this.countSpaces.t.isAlive()) {

            this.countWords.t.join();
            this.countSpaces.t.join();

            if(System.currentTimeMillis() - startTime > 1000) {
                if(this.countWords.t.isAlive()) {
                    this.countWords.t.interrupt();
                    this.countWords.t.join();
                }
                if(this.countSpaces.t.isAlive()){
                    this.countSpaces.t.interrupt();
                    this.countSpaces.t.join();
                }
            }
        }

        System.out.println("Counting is finished");

    }

    public static void main(String[] args) throws InterruptedException{

        String text = "";
        new StartCount(text).count();
    }
}
