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

    OneThread oneThread = new OneThread(0, 0, board);
    TwoThread twoThread = new TwoThread(1, 1, board);

    Thread threadOne = new Thread(oneThread);
    Thread threadTwo = new Thread(twoThread);

    Movethreads(){

        System.out.println("Create board");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = new ReentrantLock();
            }
        }
        board[0][0].lock();
        board[1][1].lock();


    }
    @Override
    public void run() {

        boolean isMove = true;
        while(isMove){
            threadOne.start();
            threadTwo.start();
            isMove = false;
        }

    }


    public static void main(String[] args) {

        Thread thread = new Thread(new Movethreads());

        thread.start();

    }


}
