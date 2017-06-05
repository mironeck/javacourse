package ru.mironenko.monitore.taskthree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by nikita on 04.06.2017.
 */
public class LinkedListThreadSafe<E> implements SimpleContainer<E> {

    /**
     * size of list
     */
    private int size = 0;
    Node<E> first;
    Node<E> last;
    Node<E> current;

    /**
     * constructor of LinkedListContainer
     */
    public LinkedListThreadSafe() {
        first = last = current = null;
    }

    /**
     * Getter of size
     * @return
     */
    public int getSize() {
        synchronized (this) {
            return size;
        }
    }

    /**
     * test, list is empty
     * @return boolean
     */
    public boolean isEmpty() {
        synchronized (this) {
            return (getSize() == 0);
        }
    }

    /**
     * Add element in the tail of list
     * @param e adding element
     */
    @Override
    public void add(E e) {
        synchronized (this) {
            Node<E> l = last;
            Node<E> node = new Node<E>(e, l, null);
            last = node;
            if (l == null) {
                first = node;
            } else {
                l.next = node;
            }
            size++;
        }
    }

    /**
     * Adds element by index
     * @param index
     * @param e assing element
     */
    public void addIndex(int index, E e) {

        synchronized (this) {
            if ((index < 0) || (index > size)) {
                throw new UnsupportedOperationException("index is wrong");
            } else if (index == 0) {
                addFirst(e);
            } else if (index == size) {
                add(e);
            } else {
                int count = 0;
                current = first;
                while (current != null && count != index) {
                    current = current.next;
                    count++;
                }
                Node<E> newNode = new Node<E>(e);
                current.previous.next = newNode;
                newNode.previous = current.previous;
                current.previous = newNode;
                newNode.next = current;
            }
        }
    }

    /**
     * Adds element in the head of list
     * @param e adding element
     */
    public void addFirst(E e) {

        synchronized (this) {
            Node<E> newNode = new Node<E>(e);
            if (first == null) {
                first = last = newNode;
            } else {

                newNode.next = first;
                first = newNode;
                newNode.next.previous = first;
            }
            size++;
        }
    }

    /**
     * Gets element by index
     * @param index
     * @return element
     */
    @Override
    public E get(int index) {

        synchronized (this) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.element;
        }
    }

    /**
     * Removes element by item
     * @param index
     * @return removed element
     */
    public E remove(int index) {

        synchronized (this) {
            E removed;
            if ((index < 0) || (index > size)) {
                throw new UnsupportedOperationException("index is wrong");
            } else if (index == 0) {
                removed = removeFirst();
            } else if (index == size) {
                removed = removeLast();
            } else {
                Node<E> node = first;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                E element = node.element;
                Node<E> next = node.next;
                Node<E> prev = node.previous;
                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                    node.previous = null;
                }
                if (next == null) {
                    last = prev;
                } else {
                    next.previous = prev;
                    node.next = null;
                }
                node.element = null;
                size--;
                removed = element;
            }
            return removed;
        }
    }

    /**
     * removes last element in list
     * @return removed element
     */
    public E removeLast() {
        synchronized (this) {
            Node<E> l = last;
            if (l == null) {
                throw new NoSuchElementException();
            }
            E element = l.element;
            Node<E> previous = l.previous;
            l.element = null;
            l.previous = null;
            last = previous;
            if (last == null) {
                first = null;
            } else {
                previous.next = null;
            }
            size--;
            return element;
        }
    }

    /**
     * removes first element in list
     * @return removed element
     */
    public E removeFirst() {

        synchronized (this) {
            Node<E> f = first;
            if (f == null) {
                throw new NoSuchElementException();
            }
            E element = f.element;
            Node<E> next = f.next;
            f.element = null;
            f.next = null;
            first = next;
            if (next == null) {
                last = null;
            } else {
                next.previous = null;
            }
            size--;
            return element;
        }
    }

    /**
     * Iterator of linkedList
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {

        Iterator it = new Iterator() {

            Node<E> current = first;
            /**
             * index of iterator
             */
            private int index = 0;
            /**
             * Hasnext as long as the indexes are not out of limits and not equals to null
             */
            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = current.element;
                    current = current.next;
                    return result;
                }
                throw new NoSuchElementException("linked list.next");
            }
        };
        return it;
    }

}
