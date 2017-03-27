package ru.mironenko.collectionspro.iterator.converttask;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by nikita on 27.03.2017.
 */
public class IteratorRealisation implements Iterator<Integer> {

    private final Iterator<Iterator<Integer>> it;
    private Iterator<Integer> currentIterator = null;

    /**
     * Constructor of IteratorRealisation
     * @param it
     */
    public IteratorRealisation(Iterator<Iterator<Integer>> it) {
        this.it = it;
    }
    @Override
    public boolean hasNext() {
        selectCurrentIterator();
        return (currentIterator != null && currentIterator.hasNext());
    }

    @Override
    public Integer next() {

        selectCurrentIterator();
        if (currentIterator == null) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }

    /**
     * Method select current iterator
     */
    private void selectCurrentIterator() {

        if(currentIterator != null && currentIterator.hasNext()) {
            return;
        }
        currentIterator = null;
        while(it.hasNext()) {
            Iterator<Integer> nextIterator = it.next();
            if (nextIterator.hasNext()) {
                currentIterator = nextIterator;
                break;
            }
        }
    }
}
