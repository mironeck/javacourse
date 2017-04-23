package ru.mironenko.collectionspro.list;

/**
 * Created by nikita on 28.03.2017.
 */
public interface SimpleContainer<E> extends Iterable<E> {

    void add(E e);
    E get(int index);
}
