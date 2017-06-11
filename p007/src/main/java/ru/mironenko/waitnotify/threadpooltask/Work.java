package ru.mironenko.waitnotify.threadpooltask;

/**
 * Created by nikita on 11.06.2017.
 */

/**
 * Class Worl to do some work
 */
public class Work {

    private int id;

    /**
     * Constructor of the class
     * @param id - id of the work
     */
    public Work(int id){
        this.id = id;
    }

    /**
     * getter of id
     * @return id ot the work
     */
    public int getId() {
        return id;
    }
}
