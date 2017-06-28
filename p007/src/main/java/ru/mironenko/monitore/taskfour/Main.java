package ru.mironenko.monitore.taskfour;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nikita on 09.06.2017.
 */
public class Main {

    public static void main(String[] args) {


       Main.init("Java");

    }

    public static void init(String keyWord) {

        String directory = "D:/Test";

        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_TREADS = 10;

        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
        ProducerFiles producerFiles = new ProducerFiles(queue, new File(directory));

        new Thread(producerFiles).start();

        for(int i = 0; i < SEARCH_TREADS; i++){
            new Thread(new Seacher(queue,keyWord)).start();
        }
    }
}
