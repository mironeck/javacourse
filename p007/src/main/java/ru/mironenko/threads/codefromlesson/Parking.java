package ru.mironenko.threads.codefromlesson;

import java.util.concurrent.Semaphore;

/**
 * Created by nikita on 25.06.2017.
 */
public class Parking {

    //парковочное место занято - true
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static final boolean[] PARKING_PLACES = new boolean[5];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {

        for(int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("Автомобиль N%d подъехал к парковке.\n", carNumber);
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                //ищем свободное место и паркуемся
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {
                        if(!PARKING_PLACES[i]) {  //если место не занято
                            PARKING_PLACES[i] = true; //занимаем его
                            parkingNumber = i;
                            System.out.printf("Автомобиль N%d припарковался на месте %d \n", carNumber, parkingNumber);
                            break;
                        }
                    }
                }

                Thread.sleep(5000); //пошли за покупками

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false; //освобождаем место
                }

                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Автомобиль N%d покинул парковку. \n", carNumber);
            }catch (InterruptedException e) {

            }
        }
    }
}


