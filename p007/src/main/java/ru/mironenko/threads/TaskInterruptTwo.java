package ru.mironenko.threads;

/**
 * Created by nikita on 25.06.2017.
 */
public class TaskInterruptTwo implements Runnable {
    @Override
    public void run() {

        try {
            System.out.println("in run() - about to workTwo()");
            workTwo();
            System.out.println("in run() - back from workTwo");
        } catch (InterruptedException e) {
            System.out.println("in run() - interrupted in work2()");
            return;
        }
        System.out.println("in run() - doing stuff after nap");
        System.out.println("in run() - leaving normally");
    }

    private void workTwo() throws InterruptedException {

        while(true){
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("C isInterrupted() = " + Thread.currentThread().isInterrupted());
                Thread.sleep(2000);
                System.out.println("D isInterrupted()= " + Thread.currentThread().isInterrupted());
            }
        }
    }

    public void work() throws InterruptedException{

        while(true) {
            for (int i = 0; i < 100000; i++){

            }
        }
    }
}
