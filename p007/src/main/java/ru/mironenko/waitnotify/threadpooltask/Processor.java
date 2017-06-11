package ru.mironenko.waitnotify.threadpooltask;

/**
 * Created by nikita on 11.06.2017.
 */

/**
 * Creates some work
 */
public class Processor implements Runnable{

    /**
     * Some work
     */
    private Work work;
    /**
     * id for initialization work
     */
    private int id;

    /**
     * Constructor of Processor, creates new work
     * @param id - number to initialize work
     */
    public Processor(int id){
        this.id = id;
        this.work = new Work(id);
    }

    @Override
    public void run() {
        add(work);
    }

    /**
     * Emulates some work
     * @param work - some work
     */
    private synchronized void add(Work work){

        System.out.println("Starting work " + work.getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed work " + work.getId());
    }
}
