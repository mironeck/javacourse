package ru.mironenko.socket.botoracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by nikita on 11.02.2017.
 */
public class ServerTest {
    private static final String LN = System.getProperty("line.separator");
    @Test
    public void whenAskExitThenExit() throws IOException{
        this.testServer("exit", "");
    }

    @Test
    public void whenAskHelloThenAnswerHello() throws IOException{
        this.testServer(
                Joiner.on(LN).join(
                        "Hello",
                        "exit"
                ),
                Joiner.on(LN).join(
                        "Hello my dear friend",
                        "",
                        ""
                )
        );

    }

    @Test
    public void whenUnsupportedAskToRepeat() throws IOException{
        this.testServer(
                Joiner.on(LN).join(
                        "unsupported ask",
                        "exit"
                ),
                Joiner.on(LN).join(
                        "repeat your question, please",
                        "",
                        ""
                )
        );
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.init();
        assertThat(out.toString(), is(expected)
        );
    }
}