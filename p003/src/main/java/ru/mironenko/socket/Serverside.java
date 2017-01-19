package ru.mironenko.socket;

//Перед реализацией в коде. Составить каркас приложения на интерфейсах. С описанием.
//        1. Разработать клиент серверное приложение на сокетах.
//        2. Серверная часть должна реализовывать следующее апи:
//        - получить список корневого каталога. Корневой каталог задается при запуске сервера.
//        - перейти в подкаталог.
//        - спуститься в родительский каталог
//        - скачать файл
//        - загрузить файл.
//        3. Клиент должен это апи уметь вызывать.
//        4. настройки портов и адреса считывать с app.properties

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by nikita on 18.01.2017.
 */
public class Serverside {

    int port = 10000;

    public static void main(String[] args) throws IOException {

        new Serverside().init();

    }

    public void init() throws IOException {

        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                InputStream socketInStr = clientSocket.getInputStream();
                OutputStream socketOutStr = clientSocket.getOutputStream();

                DataInputStream in = new DataInputStream(socketInStr);
                DataOutputStream out= new DataOutputStream(socketOutStr)
        )
        {
            String line = null;
            while(true) {
                line = in.readUTF();
                if ("меню".equals(line)) {
                    out.writeUTF(shownMenu());
                } else {
                    out.writeUTF(chooseMenuItem(line));
                    out.flush();
                }
            }


        }
    }

    private String chooseMenuItem(String line) {
        String result = null;
        if("1".equals(line)) {
            result = getRootDirectoryList();
        } else if ("2".equals(line)) {
            result = goToSubDirectory();
        }else if ("3".equals(line)) {
            result = goToParentDirectory();
        }else if("4".equals(line)) {
            result = downloadFile();
        }else if("5".equals(line)) {
            result = uploadFile();
        }

        return result;
    }

    private String getRootDirectoryList() {
        System.out.println("Список корневого каталога");
        return "Список корневого каталога";
    }

    private String goToSubDirectory() {
        System.out.println("Подкаталог");
        return "Подкаталог";
    }
    private String goToParentDirectory() {
        System.out.println("Родительский каталог");
        return "Родительский каталог";
    }

    private String uploadFile() {
        System.out.println("Загрузили файл");
        return "Загрузили файл";
    }

    private String downloadFile() {
        System.out.println("Скачали файл");
        return "Скачали файл";
    }

    private String shownMenu() {
        return "1 - получить список корневого каталога" + "\r\n" +
                "2 - перейти в подкаталог" + "\r\n" +
                "3 - спуститься в родительский каталог" + "\r\n" +
                "4 - скачать файл" + "\r\n" +
                "4 - загрузить файл";
    }





}
