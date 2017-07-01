package ru.mironenko.reentrantlocktask;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nikita on 19.06.2017.
 */

//есть массив ReentrantLock[][] board
//        и  два new Thread.
//        надо в методе run реализовать движение thread.
//        Два треда стоят на точках 00  и 11. Эти точки должны быть залочены.
//        когда мы двигаемся, мы должны блокировать новую и освобождать текущую.

/**
 * Class MoveThread makes moves of two threads
 */
public class Movethreads implements Runnable {

    /**
     * array of ReentrantLock, board.
     */
    public volatile ReentrantLock[][] board = new ReentrantLock[5][5];

    /**
     * Constructor of class. Creates new instances of ReentrantLock on the board.
     */
    Movethreads(){

        System.out.println("Create board");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = new ReentrantLock();
            }
        }

    }

    @Override
    public void run() {

        /**
         * Creates two threads
         */
        OneThread oneThread = new OneThread(0, 0, board);
        TwoThread twoThread = new TwoThread(1, 1, board);

        Thread threadOne = new Thread(oneThread);
        Thread threadTwo = new Thread(twoThread);

            threadOne.start();
            threadTwo.start();
    }


    public static void main(String[] args) {

        Thread thread = new Thread(new Movethreads());

        thread.start();

    }

}
