package ru.mironenko.collectionspro.iterator.converttask;

import java.util.Iterator;

/**
 * Created by nikita on 27.03.2017.
 */
public interface IteratorOfIterators {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
