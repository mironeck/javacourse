package ru.mironenko.collectionslite.collectionsframework;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 17.03.2017.
 */
public class ConvertListTest {

    @Test
    public void whenTwoDimensionalArrayConvertToListReturnList(){

        ConvertList convertList = new ConvertList();

        int[][] twoDarray = new int[][] {
                {1, 2, 3},
                {4, 5 ,6},
                {7, 8, 9}
        };

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 1; i <= 9; i++) {
            result.add(i);
        }

        List<Integer> checked = convertList.toList(twoDarray);
        assertThat(result, is(checked));
    }

    @Test
    public void whenListConvertToTwoDimensionalArrayReturnTDA(){

        ConvertList convertList = new ConvertList();

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 7; i++) {
            list.add(i);
        }

        int[][] result = new int[][] {
                {1, 2, 3},
                {4, 5 ,6},
                {7, 0, 0}
        };

        int [][] checked = convertList.toArray(list, 3);
        assertThat(checked, is (result));
    }

}