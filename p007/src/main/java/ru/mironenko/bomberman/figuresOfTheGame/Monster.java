package ru.mironenko.bomberman.figuresOfTheGame;


import ru.mironenko.bomberman.field.Field;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 05.07.2017.
 */
public class Monster implements BoardItem, Runnable {

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
     * Constructor of Monster
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board - game field
     */
    public Monster(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /**
     * Setter of coordinate x
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter of coordinate y
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves monster up while move is available. If move not available moves on the other side(to the right).
     * Tries lock new cell in 5 seconds, if it not possible move on the other side(to the right).
     * @throws InterruptedException
     */
    private void monstersMoveUp() throws InterruptedException {

//        this.board[this.x][this.y].lock();
        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = this.x;
        int newY = this.y + 1;

        while(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock(5, TimeUnit.SECONDS)) {
                try {
                    this.board[x][y].unlock();
                    setX(newX);
                    setY(newY);
                    Thread.currentThread().sleep(500);
                    System.out.println(Thread.currentThread().getName() + " move up on " + this.x + ":" + this.y);
                } finally {
                    this.board[newX][newY].unlock();
                }
            }
            newY++;
        }
    }

    /**
     * Moves monster down while move is available. If move not available moves on the other side(to the left).
     * Tries lock new cell in 5 seconds, if it not possible move on the other side(to the left).
     * @throws InterruptedException
     */
    private void monstersMoveDown() throws InterruptedException {

//        this.board[this.x][this.y].lock();
        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = this.x;
        int newY = this.y - 1;

        while(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock(5, TimeUnit.SECONDS)) {
                try {
                    this.board[x][y].unlock();
                    setX(newX);
                    setY(newY);
                    Thread.currentThread().sleep(500);
                    System.out.println(Thread.currentThread().getName() + " move down on " + this.x + ":" + this.y);
                } finally {
                    this.board[newX][newY].unlock();
                }
            }
            newY--;
        }
    }

    /**
     * Moves monster left while move is available. If move not available moves on the other side(to the up).
     * Tries lock new cell in 5 seconds, if it not possible move on the other side(to the up).
     * @throws InterruptedException
     */
    private void monstersMoveLeft() throws InterruptedException {

//        this.board[this.x][this.y].lock();
        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = this.x - 1;
        int newY = this.y;

        while(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock(5, TimeUnit.SECONDS)) {
                try {
                    this.board[x][y].unlock();
                    setX(newX);
                    setY(newY);
                    Thread.currentThread().sleep(500);
                    System.out.println(Thread.currentThread().getName() + " move left on " + this.x + ":" + this.y);
                } finally {
                    this.board[newX][newY].unlock();
                }
            }
            newX--;
        }
    }

    /**
     * Moves monster right while move is available. If move not available moves on the other side(to the down).
     * Tries lock new cell in 5 seconds, if it not possible move on the other side(to the down).
     * @throws InterruptedException
     */
    private void monstersMoveRight() throws InterruptedException {

//        this.board[this.x][this.y].lock();
        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = this.x + 1;
        int newY = this.y;

        while(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock(5, TimeUnit.SECONDS)) {
                try {
                    this.board[x][y].unlock();
                    setX(newX);
                    setY(newY);
                    Thread.currentThread().sleep(500);
                    System.out.println(Thread.currentThread().getName() + " move right on " + this.x + ":" + this.y);
                } finally {
                    this.board[newX][newY].unlock();
                }
            }
            newX++;
        }
    }

    /**
     * Checks if move is available
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if move available, false if not.
     */
    private boolean isMoveAvailable(int x, int y) {
        boolean result = true;
        if(x > board.length -1 || y > board[0].length -1 || x < 0 || y < 0) {
            result = false;
        }
        return result;
    }


    @Override
    public void run() {

//        this.board[this.x][this.y].lock();

        try {
            while(true) {
                monstersMoveDown();
                monstersMoveLeft();
                monstersMoveUp();
                monstersMoveRight();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
