package ru.mironenko.bomberman.figuresOfTheGame;

import ru.mironenko.bomberman.field.Field;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 15.06.2017.
 */
public class BomberMan implements Runnable{

    /**
     * Count of lives of bomberman
     */
    public static int lives = 3;
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
     * Constructor of BomberMan
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board game field
     */
    public BomberMan(int x, int y, ReentrantLock[][] board){
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /**
     * Setter of x-coordinate
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**Setter of y-coordinate
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves bomberman up.
     * @throws InterruptedException
     */
    public void bomberManMoveUp() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + "  on " + this.x + ":" + this.y);
        int newX = this.x;
        int newY = this.y - 1;

        if(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock()) {
//                try {
                    Thread.currentThread().sleep(500);
                    this.board[this.x][this.y].unlock();
                    setX(newX);
                    setY(newY);
                    System.out.println("BM moves up");
//                } finally {
//                    this.board[newX][newY].unlock();
//                }
            }
        } else {
            return;
        }
    }

    /**
     * Moves bomberman down.
     * @throws InterruptedException
     */
    public void bomberManMoveDown() throws InterruptedException {

        int newX = this.x;
        int newY = this.y + 1;

        if(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock()) {
//                try {
                    Thread.currentThread().sleep(500);
                    this.board[this.x][this.y].unlock();
                    setX(newX);
                    setY(newY);
                    System.out.println("BM moves up");
//                } finally {
//                    this.board[newX][newY].unlock();
//                }
            }
        } else {
            return;
        }
    }

    /**
     * Moves bomberman left.
     * @throws InterruptedException
     */
    public void bomberManMoveLeft() throws InterruptedException {

        int newX = this.x - 1;
        int newY = this.y;

        if(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock()) {
//                try {
                Thread.currentThread().sleep(500);
                this.board[this.x][this.y].unlock();
                setX(newX);
                setY(newY);
                System.out.println("BM moves up");
//                } finally {
//                    this.board[newX][newY].unlock();
//                }
            }
        } else {
            return;
        }
    }

    /**
     * Moves bomberman right.
     * @throws InterruptedException
     */
    public void bomberManMoveRight() throws InterruptedException {

        int newX = this.x + 1;
        int newY = this.y;

        if(isMoveAvailable(newX, newY)) {
            this.board[this.x][this.y].lock();
            if(this.board[newX][newY].tryLock()) {
//                try {
                Thread.currentThread().sleep(500);
                this.board[this.x][this.y].unlock();
                setX(newX);
                setY(newY);
                System.out.println("BM moves up");
//                } finally {
//                    this.board[newX][newY].unlock();
//                }
            }
        } else {
            return;
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

    /**
     * Drops a bomb. Creates new instance of Bomb and starts it.
     */
    public void dropTheBomb() {

        new Thread(new Bomb(this.x, this.y, this.board)).start();
    }

    /**
     * Decrements lives.
     * */
    public static void livesDecrement() {
        BomberMan.lives--;
        if(BomberMan.lives == 0) {
            Field.gameOver();
        }
    }


    @Override
    public void run() {

        try {
            bomberManMoveRight();
            bomberManMoveDown();
            bomberManMoveLeft();
            bomberManMoveLeft();
            bomberManMoveLeft();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
