package ru.mironenko.waitnotify.threadpooltask.mythreadpool;

import java.util.concurrent.BlockingQueue;

/**
 * Created by nikita on 13.06.2017.
 */

/**
 *Takes work from queue and run it
 */
public class WorkExecutor implements Runnable{

    /**
     * queue to store works
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * Constructor of the class.
     * @param workQueue - queue to store works
     */
    public WorkExecutor(BlockingQueue workQueue){
        this.workQueue = workQueue;
    }

    @Override
    public void run() {
        try{
            while(true) {
                String name = Thread.currentThread().getName();
                Runnable work = workQueue.take();
                System.out.println("Starting work by thread " + name);
                work.run();
                System.out.println("Completed work by thread " + name);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
