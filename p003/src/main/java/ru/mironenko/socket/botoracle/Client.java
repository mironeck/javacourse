package ru.mironenko.socket.botoracle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


// Важно. что Оракл может отправлять большие сообщения. Чтобы понять, когда конец сообщения он отправляет пустую строку.
/**
 * Created by nikita on 08.02.2017.
 */
public class Client {

    Socket socket;

    private String ip = "127.0.0.";
    private int port = 10000;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void init() throws IOException {

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String outgoingMessage = null;
        String incomingMessage = null;
            do {
                outgoingMessage = console.nextLine();
                out.println(outgoingMessage);
                   while(! (incomingMessage = in.readLine()).isEmpty()) {
                        System.out.println(incomingMessage);
                    }
            } while(! ("exit").equals(outgoingMessage));
    }

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 10000)
                )
        {
            new Client(socket).init();
        }
    }
}
