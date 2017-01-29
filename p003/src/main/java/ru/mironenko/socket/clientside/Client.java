package ru.mironenko.socket.clientside;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by nikita on 18.01.2017.
 */
public class Client {

    int serverPort = 10000;
    String interAdress = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        new Client().init();
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


            System.out.println(shownMenu());
            System.out.println("Выберите действие: ");
            while(true) {
                string = reader.readLine();
                out.writeUTF(string);
                out.flush();
                string = in.readUTF();
                System.out.println("Сервер прислал в ответ " + string);
                System.out.println("Выберите действие:" + "\r\n" + shownMenu());
            }
        }
    }

    private String shownMenu() {
        return "1 - получить список корневого каталога" + "\r\n" +
                "2 - перейти в подкаталог" + "\r\n" +
                "3 - спуститься в родительский каталог" + "\r\n" +
                "4 - скачать файл" + "\r\n" +
                "5 - загрузить файл";
    }
}


