package ru.mironenko.threads.codefromlesson;

/**
 * Created by nikita on 30.05.2017.
 */
public class Counter {

    private int c = 0;

    public void increment(){
        c++;
    }

    public void decrement(){
        c--;
    }

    public int value(){
        return c;
    }
    
}
