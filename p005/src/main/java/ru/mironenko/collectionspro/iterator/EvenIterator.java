package ru.mironenko.collectionspro.iterator;

import java.util.Iterator;

/**
 * Created by nikita on 27.03.2017.
 */
//Создать итератор возвращающий только четные цифры

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
             * Hasnext as array has even elements
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                for(int i = this.index; i < array.length; i++) {
                    int irr = array[i];
                    if ((irr % 2) == 0) {
                        result = true;
                    }
                }
                return result;
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
