package ru.mironenko.monitore.taskone.firstdesicion;

/**
 * Created by nikita on 31.05.2017.
 */
public class CounterThread implements Runnable {

    private Count count;

    public CounterThread(final Count count) {
        this.count = count;
    }

    @Override
    public void run() {

            for (int i = 0; i < 10000; i++) {
                count.increment();
            }
            System.out.println(Thread.currentThread()
.getName() + " " + count.getC());

    }

    public static void main(String[] args) throws InterruptedException {

        Count count = new Count();

        Thread threadA = new Thread(new CounterThread(count));
        Thread threadB = new Thread(new CounterThread(count));
        Thread threadC = new Thread(new CounterThread(count));
        Thread threadD = new Thread(new CounterThread(count));
        Thread threadE = new Thread(new CounterThread(count));

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
        threadE.join();

    }
}
