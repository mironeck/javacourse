package ru.mironenko.threads;

/**
 * Created by nikita on 19.05.2017.
 */

//Часть 1.
//        Создать программу, которая будет считать количество слов и пробелов в тексте.
//        Необходимо создать два Thread. Первый будет считать количество слов. Второй количество пробелов в тексте.
//        Вывести результат на экран.
//        Продемонстрировать, что программа выполняется параллельно. Это будет видно по выводу.

/**
 * Class CountWords has a test as a parameter of the constructor and counts words in the text
 */
public class CountWords implements Runnable {

    String text;
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
        long startTime = System.currentTimeMillis();
        String [] words = text.split(" ");
        System.out.println(String.format("The text consists of %s words", words.length));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        if(time >= 1000){
            ;
        }
    }

}
