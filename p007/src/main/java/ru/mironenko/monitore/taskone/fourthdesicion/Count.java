package ru.mironenko.monitore.taskone.fourthdesicion;

/**
 * Created by nikita on 31.05.2017.
 */
public class Count {

    private volatile int c = 0;

    public int increment() {
        c++;
        return c;
    }

    public synchronized int getC(){
        return c;
    }
}
