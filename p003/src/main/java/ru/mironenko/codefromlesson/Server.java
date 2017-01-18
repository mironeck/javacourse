package ru.mironenko.codefromlesson;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by nikita on 16.01.2017.
 */
public class Server {

    public static void main(String[] args) {

        int port = 5000; //1025-65535
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ждём подключения к сокету");
            Socket socket = serverSocket.accept();
            System.out.println("Подключение состоялось");

            InputStream socketInStr = socket.getInputStream();
            OutputStream socketOutStr = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInStr);
            DataOutputStream out= new DataOutputStream(socketOutStr);

            String string = null;

            while(true) {
                string = in.readUTF();
                System.out.println("Мы получили следующее сообщение " + string);
                System.out.println("Отправка обратно");
                out.writeUTF(string);
                out.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
