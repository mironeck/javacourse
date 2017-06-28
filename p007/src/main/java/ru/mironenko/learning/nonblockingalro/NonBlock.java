package ru.mironenko.learning.nonblockingalro;

/**
 * Created by nikita on 12.06.2017.
 */
public class NonBlock {

    private volatile boolean blockCustomer = true;

    public void toDoSomething() throws InterruptedException {
        while(this.blockCustomer){
            System.out.println(String.format("%s usefull work", Thread.currentThread().getId()));
        }
    }

    public void changeBlock(boolean enable) {
        System.out.println(String.format("%s enable", Thread.currentThread().getId()));
        this.blockCustomer = enable;
    }

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {

            }
        }.start();
    }
}
