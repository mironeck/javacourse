package ru.mironenko.threads.codefromlesson;

import java.util.concurrent.CountDownLatch;

/**
 * Created by nikita on 25.06.2017.
 */
//Рассмотрим следующий пример. Мы хотим провести автомобильную гонку. В гонке принимают участие пять автомобилей. Для начала гонки нужно, чтобы выполнились следующие условия:
//        Каждый из пяти автомобилей подъехал к стартовой прямой;
//        Была дана команда «На старт!»;
//        Была дана команда «Внимание!»;
//        Была дана команда «Марш!».
//        Важно, чтобы все автомобили стартовали одновременно.

public class Race_CountDowmLatchExample {

    //создаём CountDownLatch на 8 условий
    private static final CountDownLatch START = new CountDownLatch(8);
    //условная длина гоночной трассы
    private static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i, (int)(Math.random() * 100 + 50))).start();
            Thread.sleep(1000);
        }

        while(START.getCount() > 3) { //Проверяем, собрались ли все автомобили у стартовой линии
            Thread.sleep(100);   // Если нет, то ждём 100мс
        }

        Thread.sleep(1000);
        System.out.println("На старт!");
        START.countDown(); //команда дана, уменьшаем счетчик на 1.
        Thread.sleep(1000);
        System.out.println("Внимание!");
        START.countDown();//Команда дана, уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Марш!");
        START.countDown();//Команда дана, уменьшаем счетчик на 1
        //счетчик становится равным нулю, и все ожидающие потоки
        //одновременно разблокируются
    }


    public static class Car implements Runnable {

        private int carNumber;
        private int carSpeed; //считаем, что скорость автомобиля постоянная

        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {

            try {
                System.out.printf("Автомобиль N%d подъехал к стартовой линии.\n", carNumber);
                //автомобиль подъехал к стартовой прямой - условие выполнено. Уменьшаем счетчик на 1
                START.countDown();
                //метод await() блокирует поток, вызвавший его, до тех пор, пока
                //счетчик CountDownLatch не станет равен 0
                START.await();
                Thread.sleep(trackLength / carSpeed);
                System.out.printf("Автомобиль N%d финишировал.\n", carNumber);
            }catch (InterruptedException e) {

            }
        }
    }
}
