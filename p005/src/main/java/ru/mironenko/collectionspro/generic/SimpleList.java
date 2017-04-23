package ru.mironenko.collectionspro.generic;

import java.util.Objects;

/**
 * Created by nikita on 27.03.2017.
 */
// Доделать контейнер SimpleList<T> добавить методы add, update, delete, get(int index);

public class SimpleList<E> {

    Object[] objects;
    int index = 0;

    /**
     * Constructor of SimpleList
     * @param size
     */
    public SimpleList(int size) {
        this.objects = new Object[size];
    }

    /**
     * Method adds value to simplelist
     * @param value
     */
    public void add(E value) {
        this.objects[index++] = value;
    }

    /**
     * Method return value with index position
     * @param position
     * @return
     */
    public E get(int position) {
        return (E)this.objects[position];
    }

    /**
     * Method update value with index position to new value
     * @param position
     * @param newValue
     */
    public void update(int position, E newValue) {
        this.objects[position] = newValue;
    }

    /**
     * Method equate value with index position to null
     * @param position
     */
    public void delete(int position) {
        this.objects[position] = null;
    }
}
