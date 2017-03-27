package ru.mironenko.collectionspro.iterator;

import java.util.Iterator;

/**
 * Created by nikita on 27.03.2017.
 */
public class EvenIterator implements Iterable{

    final private int[] array;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public Iterator iterator() {

        Iterator it = new Iterator() {

            private int index = 0;
            /**
             * Hasnext as long as the indexes are not out of limits
             */
            @Override
            public boolean hasNext() {
                return array.length > index;
            }

            @Override
            public Object next() {

                while(index < array.length) {
                    int irr = array[index];
                    index++;
                    if ((irr % 2) == 0) {
                        return irr;
                    }
                }
                return null;
            }
        };
        return it;
    }
}
