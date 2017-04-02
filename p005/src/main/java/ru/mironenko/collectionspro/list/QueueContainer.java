package ru.mironenko.collectionspro.list;

/**
 * Created by nikita on 02.04.2017.
 */
public class QueueContainer<E> extends LinkedListContainer {

    /**
     * Adds element in the tail of list
     * @param e
     */
    public void push(E e) {
        add(e);
    }

    /**
     * Removes first element
     */
    public void pull(){
        removeFirst();
    }
}
