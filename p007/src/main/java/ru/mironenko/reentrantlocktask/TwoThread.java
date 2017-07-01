package ru.mironenko.reentrantlocktask;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 19.06.2017.
 */
public class TwoThread extends Thread implements BoardItem{

    private int x;
    private int y;
    private ReentrantLock[][] board;

    public TwoThread(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public void run() {

        this.board[x][y].lock();
        try {
            makeAMove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Makes a move of thread. Waiting for lock for seconds. If lock on new coordinates
     * succeed unlock last coordinates, sets new x and y.
     */
    private void makeAMove() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " on " + this.x + ":" + this.y);
        int newX = 0;
        int newY = 0;

        if(this.board[newX][newY].tryLock(1, TimeUnit.SECONDS)) {
            try{
                this.board[x][y].unlock();
                setX(newX);
                setY(newY);
                System.out.println(Thread.currentThread().getName() + " on " + this.x + ":" + this.y);
            } finally {
                this.board[newX][newY].unlock();
            }
        } else {
            System.out.printf("Move on %d:%d is not possible", newX, newY);
        }
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
