package ru.mironenko.waitnotify.customlockimplementation;

/**
 * Created by nikita on 12.06.2017.
 */
//Реализовать собственный механизм блокировок Lock. на основе synch wait notify

/**
 * Custom lock implementation
 */
public class CustomLock {

    /**
     * Flag
     */
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException{

        while(isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unLock(){
        isLocked = false;
        notify();
    }
}
