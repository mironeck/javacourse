package ru.mironenko.collectionspro.list;

/**
 * Created by nikita on 02.04.2017.
 */
/**
 * Inner class to create new elements
 * @param <E>
 */
class Node<E> {

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
}