package ru.mironenko.monitore.taskfour;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nikita on 09.06.2017.
 */
public class ProducerFiles implements Runnable {

    private File startingDirectory;
    private BlockingQueue<File> queue = null;
    public static File DUMMY = new File("");

    /**
     * Constructor of ProducerFiles
     * @param queue the blocking queue to which enumerated files are added
     * @param startingDirectory the directory in which to start the enumeration
     */
    public ProducerFiles(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {

        try{
            enumerate(startingDirectory);
            queue.put(DUMMY);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursively enumerates all files in a directory and subdirectories
     * @param directory the directory in which to start
     * @throws InterruptedException
     */
    private void enumerate(File directory) throws InterruptedException {

        File[] files = directory.listFiles();
        for(File temp : files) {
            if(temp.isDirectory()) {
                enumerate(temp);
            } else {
                queue.put(temp);
            }
        }
    }
}
