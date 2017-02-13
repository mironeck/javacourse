package ru.mironenko.socket.botoracle;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nikita on 11.02.2017.
 */
public class ClientTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskHelloAnswerIsHelloFriend() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.init();
        assertThat(out.toString(), is(""));
    }


}