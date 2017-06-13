package ru.mironenko.waitnotify.threadpooltask.mythreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nikita on 13.06.2017.
 */

/**
 * Thread pool class
 */
public class MyThreadPool {

    /**
     * Blocking queue to store some works in it
     */
    private BlockingQueue<Runnable> workQueue = null;
    /**
     * Size of blocking queue
     */
    private final int LIMIT_OF_WORKS = 10;

    /**
     * Constructor of MyThreadPool. Create and starts threads in it.
     * @param numberOfThreads - number of threads that will be execute works
     */
    public MyThreadPool(int numberOfThreads) {

        workQueue = new ArrayBlockingQueue(LIMIT_OF_WORKS);

        for(int i = 0; i < numberOfThreads; i++){
            Thread thread = new Thread(new WorkExecutor(workQueue));
            thread.start();
        }
    }

    /**
     * Adds work to queue
     * @param work - work to do
     * @throws InterruptedException
     */
    public void add(Work work) throws InterruptedException {
        workQueue.put(work);
    }

    public static void main(String[] args) throws InterruptedException {

        MyThreadPool myThreadPool = new MyThreadPool(2);

        for(int i = 0; i < 5; i++) {
            Work work = new Work(i);
            myThreadPool.add(work);
        }
    }
}
