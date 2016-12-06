package ru.mironenko.inout;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 06.12.2016.
 */
public class PalindromeTaskTest {
    @Test
    public void whenEnterWrongWordResutlIsFalse() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "Rasdfhfdg";
        final InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(word.getBytes()));
            assertThat(pt.isPalindrome(), is(false));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void whenEnterWrightWordResutlIsTrue() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "roToR";
        final InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(word.getBytes()));
            assertThat(pt.isPalindrome(), is(true));
        } finally {
            System.setIn(stdin);
        }
    }

}