package ru.mironenko.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by nikita on 18.01.2017.
 */
public class ClientSide {

    int serverPort = 10000;
    String interAdress = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        new ClientSide().init();
    }

    public void init() throws IOException{

        InetAddress inetAddress = InetAddress.getByName(interAdress);

        try (
                Socket socket = new Socket(inetAddress, serverPort);

                InputStream socketInStr = socket.getInputStream();
                OutputStream socketOutStr = socket.getOutputStream();

                DataInputStream in = new DataInputStream(socketInStr);
                DataOutputStream out= new DataOutputStream(socketOutStr);

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        )
        {
            String string = null;
            System.out.println("Введите фразу для передачи серверу: ");
            while(true) {
                string = reader.readLine();
                out.writeUTF(string);
                out.flush();
                string = in.readUTF();
                System.out.println("Сервер прислал в ответ " + string);
                System.out.println("Введите фразу для отправки на сервер:");
            }
        }

    }
}


