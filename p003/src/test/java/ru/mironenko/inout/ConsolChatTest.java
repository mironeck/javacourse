package ru.mironenko.inout;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 13.01.2017.
 */
public class ConsolChatTest {

    ByteArrayOutputStream baos;

    @Before
    public void prepareOutputStream() {
        this.baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @Test
    public void whenEnterWordChatIsAnswerSomething() throws Exception {
        String someword = String.format("word%sword", "\r\n");
        System.setIn(new ByteArrayInputStream(someword.getBytes()));
        try {
            new ConsolChat().isChatting();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.baos.toString().isEmpty(), is(false));
    }

    @Test
    public void whenEnterStopChatIsSilent() throws Exception {
        String stopWord = String.format("стоп%sword", "\r\n");
        System.setIn(new ByteArrayInputStream(stopWord.getBytes()));
        try {
            new ConsolChat().isChatting();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.baos.toString().isEmpty(), is(true));
    }

    @Test
    public void whenEnterContinueChatIsAnswer() throws Exception {
        String continueWord = String.format("стоп%sword%sпродолжить", "\r\n", "\r\n");
        System.setIn(new ByteArrayInputStream(continueWord.getBytes()));
        try {
            new ConsolChat().isChatting();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.baos.toString().isEmpty(), is(false));
    }

    @Test
    public void whenEnterEndChatIsEnded() throws Exception {
        String endWord = String.format("закончить%sword", "\r\n");
        System.setIn(new ByteArrayInputStream(endWord.getBytes()));
        try {
            new ConsolChat().isChatting();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.baos.toString().isEmpty(), is(true));
    }

}