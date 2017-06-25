package ru.mironenko.reentrantlocktask;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 19.06.2017.
 */
public class TwoThread extends Thread implements BoardItem{

    private int x;
    private int y;
    private volatile ReentrantLock[][] board;

    public TwoThread(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public void run() {

        this.board[x][y].lock();
        makeAMove();

    }

    private void makeAMove(){

        System.out.println(Thread.currentThread().getName() + " on " + this.x + ":" + this.y);
        int newX = 2;
        int newY = 2;

        this.board[newX][newY].lock();
        this.board[this.x][this.y].unlock();

        setX(newX);
        setY(newY);
        System.out.println(Thread.currentThread().getName() + " on " + this.x + ":" + this.y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
