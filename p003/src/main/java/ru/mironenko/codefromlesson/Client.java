package ru.mironenko.codefromlesson;

import java.io.*;
import java.net.InetAddress;
import java.net.*;

/**
 * Created by nikita on 16.01.2017.
 */
public class Client {

    public static void main(String[] args) {

        int servPort = 5000;
        String interAdress = "127.0.0.1";

        try {

            InetAddress inetAddress = InetAddress.getByName(interAdress);
            System.out.println("Подключаемся к серверу");
            Socket socket = new Socket(inetAddress, servPort);

            InputStream socketInStr = socket.getInputStream();
            OutputStream socketOutStr = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInStr);
            DataOutputStream out= new DataOutputStream(socketOutStr);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
