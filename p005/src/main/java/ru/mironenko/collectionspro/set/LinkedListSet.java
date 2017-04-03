package ru.mironenko.collectionspro.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by nikita on 03.04.2017.
 */
public class LinkedListSet<E> {

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
    public LinkedListSet() {
        first = last = current = null;
    }

    /**
     * Getter of size
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * test, list is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return (getSize() == 0);
    }

    /**
     * Add element in the tail of list
     * @param e adding element
     */

    public boolean add(E e) {
        Node<E> l = last;
        Node<E> node = new Node<E>(e, l, null);
        Node<E> nodeTmp = first;

        boolean isAdded = false;
        boolean flag = false;

        for(int i = 0; i < size; i++) {
            if(nodeTmp.getValue().equals(node.getValue())){
                flag = true;
                break;
            }
            nodeTmp = nodeTmp.next;
        }
        if(!flag) {
            last = node;
            if(l == null) {
                first = node;
            } else {
                l.next = node;
            }
            size++;
            isAdded = true;
        }
        return isAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListSet)) return false;

        LinkedListSet<?> that = (LinkedListSet<?>) o;

        if (getSize() != that.getSize()) return false;
        if (!first.equals(that.first)) return false;
        if (!last.equals(that.last)) return false;
        return current.equals(that.current);

    }

    @Override
    public int hashCode() {
        int result = getSize();
        result = 31 * result + first.hashCode();
        result = 31 * result + last.hashCode();
        result = 31 * result + current.hashCode();
        return result;
    }

    public Iterator<E> iterator() {

        Iterator it = new Iterator() {

            Node<E> current = first;

            /**
             * HasNext as long as the indexes are not out of limits and not equals to null
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
