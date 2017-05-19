package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */

//Часть 1.
//        Создать программу, которая будет считать количество слов и пробелов в тексте.
//        Необходимо создать два Thread. Первый будет считать количество слов. Второй количество пробелов в тексте.
//        Вывести результат на экран.
//        Продемонстрировать, что программа выполняется параллельно. Это будет видно по выводу.


public class CountWords implements Runnable {

    String text;


    public CountWords(String fileName) {
        this.text = fileName;

    }

    @Override
    public void run() {

        String [] words = text.split(" ");
        System.out.println(String.format("The text consists of %s words", words.length));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Thread(new CountSpaces("This text includes five simple spaces")).start();
        new Thread(new CountWords("This text consist of six words")).start();
    }
}
