package ru.mironenko.monitore.taskthree;

import java.util.Iterator;

/**
 * Created by nikita on 04.06.2017.
 */
public class ArrayListThreadSafe<E> implements SimpleContainer<E>{

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int countOfElements = 0;


    /**
     * Default constructor of ArrayListContainer
     */
    public ArrayListThreadSafe(){
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor with capacity
     * @param capacity
     */
    public ArrayListThreadSafe(int capacity) {
        this.array = new Object[capacity];
    }

    /**
     * Method increases capacity in two fold
     */
    private void increaseCapacity(){
        Object[] newArray = new Object[this.array.length * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }


    /**
     * Add element in array
     * @param e added object
     */
    @Override
    public void add(E e) {
        synchronized(this) {
            if (this.countOfElements == this.array.length) {
                increaseCapacity();
            }
            this.array[countOfElements++] = e;
        }
    }

    /**
     * Returns element from array by index
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        synchronized (this) {
            if ((index < 0) || (index >= this.array.length)) {
                System.out.println("Wrong index");
            }
            return (E) this.array[index];
        }
    }

    /**
     * Returns size of list
     * @return array size
     */
    public int size(){
        synchronized (this) {
            return this.array.length;
        }
    }

    /**
     * Iterator
     * @return iterator
     */
    @Override
    public Iterator iterator() {
        Iterator it = new Iterator() {

            /**
             * index of iterator
             */
            private int index = 0;
            /**
             * HasNext as long as the indexes are not out of limits and not equals to null
             */
            @Override
            public boolean hasNext() {
                return ((array.length > index) && (array[index] != null));
            }

            @Override
            public E next() {

                while(index < array.length) {
                    return (E) array[index++];
                }
                return null;
            }
        };
        return it;
    }

}
