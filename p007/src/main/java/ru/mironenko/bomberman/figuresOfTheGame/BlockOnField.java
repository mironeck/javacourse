package ru.mironenko.bomberman.figuresOfTheGame;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 15.06.2017.
 */
public class BlockOnField implements BoardItem, Runnable{

    /**
     *  x - coordinates of block
     */
    private int x;
    /**
     * y - coordinate of block
     */
    private int y;

    private final ReentrantLock[][] board;

    /**
     * Constructor of block
     * @param x - x - coordinates of block
     * @param y - y - coordinates of block
     * @param board - game field
     */
    public BlockOnField(final int x, final int y, final ReentrantLock[][] board){
        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public void run() {

        this.board[x][y].lock();
    }
}
