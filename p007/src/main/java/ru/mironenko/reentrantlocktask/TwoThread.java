package ru.mironenko.reentrantlocktask;

import sun.awt.windows.ThemeReader;

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
        this.board[x][y].lock();
    }

    @Override
    public void run() {

        System.out.println("Thread two smth doing...");

    }

    public void makeMove(int newX, int newY){

        this.board[newX][newY].lock();
        this.board[this.x][this.y].unlock();
        setX(newX);
        setY(newY);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
