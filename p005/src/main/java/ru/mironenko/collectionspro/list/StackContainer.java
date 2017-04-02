package ru.mironenko.collectionspro.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by nikita on 02.04.2017.
 */
public class StackContainer<E> extends LinkedListContainer{

    /**
     * Adds element in the head of list
     * @param e
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     * Removes first element
     */
    public void pull(){
        removeLast();
    }



}
