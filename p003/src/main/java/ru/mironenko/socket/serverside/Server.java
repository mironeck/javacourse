package ru.mironenko.socket.serverside;

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
public class Server {

    int port = 10000;
    private static String rootDirectory = "C:";

    public Server(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        new Server(Server.rootDirectory).init();

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

                out.writeUTF(chooseMenuItem(line, in, out));
                out.flush();
            }
        }
    }


    private String chooseMenuItem(String line, DataInputStream in, DataOutputStream out) throws IOException{
        String result = null;
        if("1".equals(line)) {
            result = getRootDirectoryList();
        } else if ("2".equals(line)) {
            result = goToSubDirectory(in, out);
        }else if ("3".equals(line)) {
            result = goToParentDirectory(in, out);
        }else if("4".equals(line)) {
            result = downloadFile();
        }else if("5".equals(line)) {
            result = uploadFile();
        }

        return result;
    }

    private String getRootDirectoryList() {
        String result = null;
        File file = new File("C://");
        for(File item : file.listFiles()){
            if(item.isDirectory()){
                result = result + item.getName() + "\r\n";
            }
        }
        return result;
    }

    private String goToSubDirectory(DataInputStream in, DataOutputStream out) throws IOException {

        out.writeUTF("Введите имя каталога");
        out.flush();
        String nameSubDirectory = in.readUTF();

        String result = null;
        File file = new File("C:" + File.separator + nameSubDirectory);
        for(File item : file.listFiles()){
            if(item.isDirectory()){
                result = result + item.getName() + "\r\n";
            }
        }
        return result;
    }
    private String goToParentDirectory(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF("Введите имя каталога");
        out.flush();
        String nameSubDirectory = in.readUTF();

        String result = null;
        File file = new File("C:" + File.separator + nameSubDirectory);
        result = file.getParentFile().toString();
        return result;
    }

    private String uploadFile() {
        System.out.println("Загрузили файл");
        return "Загрузили файл";
    }

    private String downloadFile() {
        System.out.println("Скачали файл");
        return "Скачали файл";
    }







}
