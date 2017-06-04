package ru.mironenko.monitore.taskone.firstdesicion;

/**
 * Created by nikita on 27.05.2017.
 */
//Сделать класс многопоточный счетчик Count#int incremant() продемонстрировать работу через два потока.
//Дополнительное задание, можно не делать. предложить 4 различных решения.


public class Count {

    private int c = 0;

    public synchronized int increment() {
        c++;
        return c;
    }

    public synchronized int getC() {
        return c;
    }
}
