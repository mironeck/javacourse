package ru.mironenko.bomberman.figuresOfTheGame;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 06.07.2017.
 */
public class Bomb implements Runnable, BoardItem {


    /**
     * X-coordinate
     */
    private int x;
    /**
     * y-coordinate
     */
    private int y;
    /**
     * Game field
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor of Bomb
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board game field
     */
    public Bomb(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /**
     * Locks cell where bomb dropped, waits 3 seconds, then unlock this cell.
     */
    private void blowUpBomb() {

        this.board[this.x][this.y].lock();
        try {
            Thread.currentThread().sleep(3000);
            System.out.println("Blow!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.board[this.x][this.y].unlock();
        }
    }


    @Override
    public void run() {

        blowUpBomb();
    }
}
