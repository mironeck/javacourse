package ru.mironenko.reentrantlocktask;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 19.06.2017.
 */
public class OneThread extends Thread implements BoardItem {

    private int x;
    private int y;
    private volatile ReentrantLock[][] board;

    public OneThread(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public void run() {

        this.board[x][y].lock();
        makeAMove();

    }

    /**
     * Makes a move of thread
     */
    private void makeAMove(){

        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = 1;
        int newY = 0;

        if (!tryLock(newX, newY)){
            this.board[newX][newY].lock();
            this.board[this.x][this.y].unlock();
        } else {
            System.out.println("Move is not possible");
        }


        setX(newX);
        setY(newY);
        System.out.println(Thread.currentThread().getName() + " on " + this.x + ":" + this.y);
    }

    /**
     * Checks if this cell is locked
     * @param newX
     * @param newY
     * @return true if locked false if not
     */
    private boolean tryLock(int newX, int newY){
        return this.board[newX][newY].isLocked();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
