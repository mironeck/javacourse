package ru.mironenko.collectionspro.set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by nikita on 03.04.2017.
 */

/**
 * Simple implementation of set on array
 * @param <E>
 */
public class ArraySet<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int countOfElements = 0;

    /**
     * Default constructor of ArrayListContainer
     */
    public ArraySet(){
        this.array = new Object[DEFAULT_CAPACITY];
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
     * Adds elements in set
     * @param e adding element
     * @return boolean is element added
     */
    public boolean add(E e) {
        boolean isAdded = false;
        boolean flag = false;

        if(this.countOfElements == this.array.length) {
            increaseCapacity();
        }

        for(int i = 0; i < this.array.length; i++) {
            if (e.equals(this.array[i])) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.array[this.countOfElements++] = e;
            isAdded = true;
        }
        return isAdded;
    }


    /**
     * Returns size of list
     * @return array size
     */
    public int size(){
        return this.array.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArraySet)) return false;

        ArraySet<?> arraySet = (ArraySet<?>) o;

        if (countOfElements != arraySet.countOfElements) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, arraySet.array);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(array);
        result = 31 * result + countOfElements;
        return result;
    }

    /**
     * Iterator
     * @return iterator
     */

    public Iterator iterator() {
        Iterator it = new Iterator() {

            /**
             * index of iterator
             */
            private int index = 0;
            /**
             * Hasnext as long as the indexes are not out of limits and not equals to null
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
