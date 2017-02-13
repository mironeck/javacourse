package ru.mironenko.socket.botoracle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//1. Создать бот - мудрый Оракл.
// Сервер должен отвечать на простые вопросы. Если Оралку сказали пока. То приложение выключается.
/**
 * Created by nikita on 08.02.2017.
 */
public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * The method for initialisation
     * @throws IOException
     */
    public void init() throws IOException {

        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask = null;
        do {
            System.out.println("Wait command...");
            ask = in.readLine();
            System.out.println(ask);
            if ("Hello".equals(ask)) {
                out.println("Hello my dear friend");
                out.println();
            } else if (! ("exit".equals(ask))) {
                out.println("repeat your question, please");
                out.println();
            }
        } while (! ("exit".equals(ask)));

    }

    public static void main(String[] args) throws IOException {
        try ( final Socket socket = new ServerSocket(5000).accept() ) {

            new Server(socket);
        }
    }


}
