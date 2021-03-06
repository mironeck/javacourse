package ru.mironenko.collectionslite.collectionsframework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nikita on 16.03.2017.
 */

//Вам необходимо создать класс ConvertList.
//        В нём написать 2 метода:
//public List<Integer> toList (int[][] array) {} - в метод приходит двумерный массив целых чисел,
// необходимо пройтись по всем элементам массива и добавить их в List<Integer>.
//public int[][] toArray (List<Integer> list, int rows) {} - метод toArray должен равномерно разбить лист
// на количество строк двумерного массива. В методе toArray должна быть проверка -
// если количество элементов не кратно количеству строк - оставшиеся значения в массиве заполнять нулями.
// Например в результате конвертации листа со значениями (1,2,3,4,5,6,7) с разбиением на 3 строки
// должен получиться двумерный массив {{1, 2, 3} {4, 5, 6} {7, 0 ,0}}

/**
 * ConvertList class for convert arrayList to a two dimensional array and TDA to arrayList
 */
public class ConvertList {

    /**
     * Method convert two dimensional array to an arrayList
     * @param twoDarray
     * @return arrayList
     */
    public List<Integer> toList (int[][] twoDarray) {

        List<Integer> list = new ArrayList<>();

//        for(Integer[] array : twoDarray) {
//            list.addAll(Arrays.asList(array));
//        }

        for (int i = 0; i < twoDarray.length; i++) {
            for (int j = 0; j < twoDarray.length; j++) {
                list.add(twoDarray[i][j]);
            }
        }
        return list;
    }

    /**
     * Method to convert arrayList to a two dimensional array
     * @param list
     * @param rows
     * @return two dimensional array
     */
    public int[][] toArray (List<Integer> list, int rows) {

        int[][] array = new int[rows][rows];

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (!iterator.hasNext()) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = iterator.next();
                }
            }
        }
        return array;
    }


    /**
     * Method return list of Integers from list of int arrays
     * @param list
     * @return arrayList of Integers
     */
    public List<Integer> convert (List<int[]> list) {

        List<Integer> result = new ArrayList<>();
        for(int[] array : list) {
            for (int i = 0; i < array.length; i++) {
                result.add(array[i]);
            }
        }
        return result;
    }

}
