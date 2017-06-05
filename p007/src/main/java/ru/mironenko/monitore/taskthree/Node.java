package ru.mironenko.monitore.taskthree;

/**
 * Created by nikita on 05.06.2017.
 */
/**
 * Class to create new elements
 * @param <E>
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
}
