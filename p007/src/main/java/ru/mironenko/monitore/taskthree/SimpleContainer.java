package ru.mironenko.monitore.taskthree;

/**
 * Created by nikita on 05.06.2017.
 */
public interface SimpleContainer<E> extends Iterable<E> {

    void add(E e);
    E get(int index);
}
