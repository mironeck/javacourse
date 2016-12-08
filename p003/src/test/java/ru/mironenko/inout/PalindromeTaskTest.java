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
    public void whenEnterRightWordResutlIsWord() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "OddWord";
        final InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(word.getBytes()));
            assertThat(pt.enterTheWord(), is("OddWord"));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void whenEnterWrongWord() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "Rasdfhfdgm";
        final InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(word.getBytes()));
            assertThat(pt.enterTheWord(), is(""));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void whenWordPalindromResutlIsTrue() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "rotoR";

        assertThat(pt.isPalindrome(word), is (true));
    }

    @Test
    public void whenWordNotPalindromResutlIsFaulse() throws Exception {
        final PalindromeTask pt = new PalindromeTask();
        final String word = "rotttow";

        assertThat(pt.isPalindrome(word), is (false));
    }

}