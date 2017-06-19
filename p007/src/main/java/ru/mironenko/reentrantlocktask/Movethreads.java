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


public class Movethreads implements Runnable {

    ReentrantLock[][] board = new ReentrantLock[5][5];

    @Override
    public void run() {

    }


    public void makeMoveOnBoard(BoardItem r, int newX, int newY){

        r.makeMove(newX, newY);

    }

    public static void main(String[] args) {

        ReentrantLock[][] board = new ReentrantLock[5][5];

        Thread threadOne = new Thread(new OneThread(0, 0, board));
        Thread threadTwo = new Thread(new TwoThread(1, 1, board));


    }


}
