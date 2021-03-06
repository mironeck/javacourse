package ru.mironenko.collectionspro.iterator;

import java.util.Iterator;

/**
 * Created by nikita on 27.03.2017.
 */
public class SimpleNumbersIterator implements Iterable {

    final private int[] array;

    public SimpleNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public Iterator iterator() {

        Iterator it = new Iterator() {

            private int index = 0;
            /**
             * HasNext as long as the array has simple digits
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = index; i < array.length; i++) {
                    int irr = array[i];
                    /**
                     * Count of denominators
                     */
                    int denominators = 0;
                    if (irr > 1) {
                        for (int j = 2; j < irr; j++) {
                            if ((irr % j) == 0) {
                                denominators++;
                            }
                        }
                        if (denominators == 0) {
                            result = true;
                        }
                    }
                }
                return result;
            }

            @Override
            public Object next() {

                while(index < array.length) {
                    int irr = array[index];
                    index++;
                    /**
                     * Count of denominators
                     */
                    int denominators = 0;

                    if (irr > 1) {
                        for (int i = 2; i < irr; i++) {
                            if ((irr % i) == 0) {
                                denominators++;
                            }
                        }
                        if (denominators == 0) {
                            return irr;
                        }
                    }
                }
                return null;
            }
        };
        return it;
    }
}
