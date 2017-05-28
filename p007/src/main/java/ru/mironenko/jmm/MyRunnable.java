package ru.mironenko.jmm;

/**
 * Created by nikita on 27.05.2017.
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {

        methodOne();
    }

    private void methodOne() {

        MySharedObject localVariable2 = MySharedObject.sharedInstance;

        for (int i = 0; i < 10000; i++) {
            localVariable2.object2++;
        }
        System.out.println(localVariable2.object2);
    }


    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new MyRunnable());
        Thread two = new Thread(new MyRunnable());
        Thread three = new Thread(new MyRunnable());

        one.start();
        two.start();
        three.start();

        one.join();
        two.join();
        three.join();
    }
}
