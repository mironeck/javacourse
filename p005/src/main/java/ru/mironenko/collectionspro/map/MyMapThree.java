package ru.mironenko.collectionspro.map;

import java.util.Iterator;

/**
 * Created by nikita on 09.04.2017.
 */
public class MyMapThree<T, V> implements Iterable<V>{

    private Entry<T, V>[] buckets;
    private int DEFAULT_CAPACITY = 16;
    private int size = 0;

    public int getSize() {
        return size;
    }

    /**
     * Default constructor
     */
    public MyMapThree() {
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    /**
     * Constructor creates buckets[] with certain capacity
     * @param capasity
     */
    public MyMapThree(int capasity) {
        buckets = new Entry[capasity];
    }

    /**
     * throws IllegalArgumentException if key is null
     * @param key
     */
    private void ifKeyIsNull(T key) {
        if(key == null) {
            throw new IllegalArgumentException("key cant be null");
        }
    }

    /**
     * Calculates index for entry
     * @param key
     * @return
     */
    public int indexForkey(T key) {

        return key.hashCode() % buckets.length;

    }

    /**
     * Insert pair in map
     * if key is null throws IllegalArgumentException
     * @param key
     * @param value
     * @return
     */
    public boolean insert(T key, V value) {

        boolean result = false;

        ifKeyIsNull(key);

        int index = indexForkey(key);
        Entry<T, V> entry = buckets[index];

        if (entry != null) {
            boolean done = false;

            while(!done) {
                if(key.equals(entry.getKey())) {
                    entry.setValue(value);
                    done = true;
                    result = true;
                } else if(entry.getNext() == null) {
                    entry.setNext(new Entry<T, V>(key, value));
                    done = true;
                    result = true;
                    size++;
                }
                entry.getNext();
            }
        } else {
            buckets[index] = new Entry<T, V>(key, value);
            result = true;
            size++;
        }
        return  result;
    }

    /**
     * gets value by key
     * @param key
     * @return value
     */
    public V get(T key) {
        ifKeyIsNull(key);
        Entry<T, V> entry = buckets[indexForkey(key)];
        while(entry != null && !key.equals(entry.getKey())) {
            entry.getNext();
        }
        return entry.getValue();
    }

    /**
     * Delete element by key
     * @param key
     * @return boolean
     */
    public boolean delete(T key) {
        boolean result = false;

        ifKeyIsNull(key);
        int index = indexForkey(key);
        Entry<T, V> entry =  buckets[index];
        Entry<T, V> previous = null;

        do{
            if(previous == null) {
                buckets[index] = buckets[index].getNext();
                result = true;
                size--;
            } else {
                previous.setNext(entry.getNext());
                result = true;
                size--;
            }
            previous = entry;
            entry = entry.getNext();
        } while(entry != null && !key.equals(entry.getKey()));

        return result;
    }

    /**
     *
     * @return Iterator for MyMapThree
     */
    @Override
    public Iterator<V> iterator() {

        Iterator it = new Iterator() {

            Entry<T, V> entry = null;
            Entry<T, V>[] table = buckets;
            int index = 0;

            /**
             * Returns true if map has next element
             * @return boolean
             */
            @Override
            public boolean hasNext() {

                boolean result = false;

                if(entry != null) {
                    result = entry.getNext() != null;
                }
                if (entry == null || !result) {
                    while(index < table.length) {
                        if(table[index] != null){
                            result = true;
                            break;
                        }
                        index++;
                    }
                }
                return result;
            }

            /**
             * Returns Value of next element
             * @return V value
             */
            @Override
            public V next() {

                Entry<T, V> result = entry;

                if(entry != null) {
                    result = entry.getNext();
                } else {
                    while(index < table.length) {
                        if(table[index] != null) {
                            result = table[index++];
                            break;
                        }
                        index++;
                    }
                }

                return result.getValue();

            }

        };
        return it;
    }


    /**
     * Class creates nodes with pairs key-value
     * @param <T> key
     * @param <V> value
     */
    private static class Entry<T, V> {
        /**
         * link to the next entry (node)
         */
        private Entry<T, V> next;
        private final T key;
        private V value;

        /**
         * Constructor
         * @param key
         * @param value
         */
        public Entry(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry<T, V> getNext() {
            return next;
        }

        public void setNext(Entry<T, V> next) {
            this.next = next;
        }

        public T getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;

            return entry.key.equals(key) && entry.value.equals(value);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (getKey() != null ? getKey().hashCode() : 0);
            result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
            return result;
        }
    }
}
