package ru.mironenko.waitnotify.threadpooltask.mythreadpool;

/**
 * Created by nikita on 11.06.2017.
 */

/**
 * Class Work to do some work
 */
public class Work implements Runnable{

    /**
     * id - number of a work
     */
    private int id;

    /**
     * Constructor of the class
     * @param id - id of the work
     */
    public Work(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("start executing of work number " + id);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End executing of work number " + id);
    }
}
