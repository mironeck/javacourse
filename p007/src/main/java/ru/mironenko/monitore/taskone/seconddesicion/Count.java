package ru.mironenko.monitore.taskone.seconddesicion;

/**
 * Created by nikita on 31.05.2017.
 */
public class Count {

    private int c = 0;

    public int increment() {
        synchronized (this) {
            c++;
            return c;
        }
    }

    public int getC(){
        synchronized (this) {
            return c;
        }
    }
}
