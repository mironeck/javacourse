package ru.mironenko.collectionspro.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by nikita on 02.04.2017.
 */
public class StackContainer<E> extends LinkedListContainer{

    /**
     * Adds element in the tail of list
     * @param e
     */
    public void push(E e) {
        add(e);
    }

    /**
     * Removes last element
     * @return E element
     */
    public E pull(){
        return (E) removeLast();
    }

}
