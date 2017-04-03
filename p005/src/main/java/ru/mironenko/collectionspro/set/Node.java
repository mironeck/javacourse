package ru.mironenko.collectionspro.set;

/**
 * Created by nikita on 03.04.2017.
 */
public class Node<E> {

    E element;
    Node<E> previous;
    Node<E> next;

    /**
     * Constructor
     * @param element
     */
    Node (E element){
        this.element = element;
    }

    /**
     * Constructor
     * @param element
     * @param previous
     * @param next
     */
    Node(E element, Node<E> previous, Node<E> next) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }

    public E getValue() {
        return this.element;
    }
}
