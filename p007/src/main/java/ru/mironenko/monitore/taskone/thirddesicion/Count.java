package ru.mironenko.monitore.taskone.thirddesicion;

/**
 * Created by nikita on 31.05.2017.
 */
public class Count {

    private int c = 0;

    Object lock = new Object();

    public int increment() {
        synchronized (lock) {
            c++;
            return c;
        }
    }

    public int getC(){
        return c;
    }
}
