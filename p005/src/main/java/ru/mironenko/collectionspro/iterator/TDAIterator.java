package ru.mironenko.collectionspro.iterator;

import java.util.Iterator;

/**
 * Created by nikita on 27.03.2017.
 */
public class TDAIterator implements Iterable {

    /**
     * Two dimensional array
     */
    final private int[][] value ;

    /**
     * Construct list from a two dimensional array of ints
     * @param value
     */
    public TDAIterator(int[][] value) {
        this.value = value;
    }


    @Override
    public Iterator iterator() {

        Iterator it = new Iterator() {

            //Array row and column indexes
            private int i = 0;
            private int j = 0;

            /**
             * Hasnext as long as the indexes are not out of limits
             */
            @Override
            public boolean hasNext() {
                return i < value.length && j < value.length;
            }

            /**
             * the method reset indexes if row (array) is ended
             */
            private void resetIndexes()
            {
                if (j == value[i].length)
                {
                    j = 0;
                    i++;
                }
            }

            @Override
            public Object next() {

                while(i < value.length) {
                    while(j < value[i].length) {
                        int irr = value[i][j];
                        j++;
                        resetIndexes();
                        return irr;
                    }
                    i++;
                }
                return null;
            }
        };
        return it;
    }


    public static void main(String[] args) {
        int [][] array = new int[][]{
                {1, 2},
                {3, 4}
        };

        TDAIterator tda = new TDAIterator(array);

        Iterator iterator = tda.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }
}
