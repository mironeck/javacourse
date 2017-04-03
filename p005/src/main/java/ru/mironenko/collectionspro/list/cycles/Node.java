package ru.mironenko.collectionspro.list.cycles;

/**
 * Created by nikita on 03.04.2017.
 */

/**
 * Node class
 * @param <T>
 */
public class Node<T> {

    T value;
    /**
     * link to next element
     */
    Node<T> next;

    /**
     * Constructor
     * @param value
     */
    public Node(T value) {
        this.value = value;
    }
}
