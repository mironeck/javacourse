package ru.mironenko.inout;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 01.11.2016.
 */
public class EvenNumberTest {
    @Test
    public void testWhenNumberIsEvenShouldReturnTrue() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new StringBufferInputStream("2")));

        boolean result = EvenNumber.isNumber(reader);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void testWhenNumberIsOddShouldReturnFaulse() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new StringBufferInputStream("3")));

        boolean result = EvenNumber.isNumber(reader);
        boolean checked = false;

        assertThat(result, is(checked));
    }

}