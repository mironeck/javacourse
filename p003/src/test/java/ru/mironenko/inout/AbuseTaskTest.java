package ru.mironenko.inout;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 02.11.2016.
 */
public class AbuseTaskTest {
    @Test
    public void whenDeleteAbuseWords() throws Exception {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(result));

        ByteArrayInputStream in = new ByteArrayInputStream("I Havebuck amuck Headuckdache".getBytes());
        String[] abuse = new String[]{"buck", "muck", "duck"};

        new AbuseTask().dropAbuses(in, result, abuse);
        assertThat(result.toString(),is("I Have a Headache"));

    }

}