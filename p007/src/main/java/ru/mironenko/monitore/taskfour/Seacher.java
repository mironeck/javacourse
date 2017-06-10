package ru.mironenko.monitore.taskfour;

/**
 * Created by nikita on 09.06.2017.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * The class searches files for a given key word
 */

public class Seacher implements Runnable {

    private BlockingQueue<File> queue;
    private String keyword;
    public static boolean isFind = false;

    /**
     * Construct of Searcher
     * @param queue the queue from which to take a files
     * @param keyword the keyword to look for
     */
    public Seacher(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }


    @Override
    public void run() {


            try {
//                boolean done = false;
                    while(!Seacher.isFind ) {
//                        if(Thread.currentThread().isInterrupted()) {
//                            return;
//                        }
                        File file = queue.take();
                        if (file == ProducerFiles.DUMMY) {
                            queue.put(file);
                            Seacher.isFind = true;
//                            done = true;
                        } else {
                            search(file);
                        }
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }


    public void search(File file) throws IOException{

        Scanner in = new Scanner(new FileInputStream(file));

        while(in.hasNextLine() && Thread.currentThread().isAlive()) {
            String line = in.nextLine();
            if(line.contains(keyword)) {
                Seacher.isFind = true;
                Thread.currentThread().interrupt();
                System.out.println("The search completed in " + file.getAbsolutePath() + " " + Thread.currentThread().getName());
            }
        }
        in.close();
    }
}
