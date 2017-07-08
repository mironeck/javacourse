package ru.mironenko.bomberman.field;

import ru.mironenko.bomberman.figuresOfTheGame.BlockOnField;
import ru.mironenko.bomberman.figuresOfTheGame.BomberMan;
import ru.mironenko.bomberman.figuresOfTheGame.Monster;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 15.06.2017.
 */

/**
 * Field of the game. Creates monsters and blocks on the field.
 */
public class Field implements Runnable {

    /**
     * Board length
     */
    private final int boardLength;
    /**
     * Board height
     */
    private final int boardHeight;
    /**
     * Number of monsters
     */
    private final int numberOfMonsters;

    /**
     * Board. 2d array of ReentrantLock
     */
    public volatile ReentrantLock[][] board;

    /**
     * Constructor of the Field.
     * @param boardLength - board length
     * @param boardHeight - board height
     * @param numberOfMonsters - number of monsters
     */
    public Field(int boardLength, int boardHeight, int numberOfMonsters){
        this.boardLength = boardLength;
        this.boardHeight = boardHeight;
        this.numberOfMonsters = numberOfMonsters;

        /**
         * Initialize of board, creates instances of ReentrantLocks
         */
        board = new ReentrantLock[this.boardLength][this.boardHeight];
        for(int i = 0; i < this.boardHeight; i++) {
            for(int j = 0; j < this.boardLength; j++){
                board[i][j] = new ReentrantLock();
            }
        }

    }


    @Override
    public void run() {

        Thread thread4 = new Thread(new BlockOnField(2, 2, board));
        Thread thread5 = new Thread(new BlockOnField(3, 3, board));
        Thread thread6 = new Thread(new BlockOnField(4, 4, board));
        thread4.start();
        thread5.start();
        thread6.start();

        createMonsters(this.numberOfMonsters);

    }

    /**
     * Creates monsters
     * @param count - number of monsters to create.
     */
    private void createMonsters(int count) {
        for(int i = 0; i < count; i++) {
            Thread thread = new Thread(new Monster(i, 1, board));
            thread.start();
        }
    }

    /**
     * Prints "Game over" if game is over.
     */
    public static void gameOver() {
        System.out.println("GAME OVER");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Field(5, 5, 2));
        thread.start();
    }
}
