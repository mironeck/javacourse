package ru.mironenko.socket.filemanager.serverside;

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

    private static String DIRECTORYNAME = "C:";
    private static String NAMESUBDIRECTORY = null;

    public static void main(String[] args) throws IOException, InterruptedException {

       // System.out.println(new Server().getRootDirectoryName());
         new Server().init();

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


//    private String getRootDirectoryName() throws IOException {
//
//        Properties prop = new Properties();
//        InputStream io = getClass().getClassLoader().getResourceAsStream("servertask.properties");
//        prop.load(io);
//        return prop.getProperty("rootdirectory.path");
//    }

    private String chooseMenuItem(String line, DataInputStream in, DataOutputStream out) throws IOException{
        String result = null;
        if("1".equals(line)) {
            getRootDirectoryList(out);
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

    private void getRootDirectoryList(DataOutputStream out) throws IOException {
        String result = null;
        File file = new File(DIRECTORYNAME);
        for(File item : file.listFiles()){
            if(item.isDirectory()){
                result = result + item.getName() + "\r\n";
            }
        }
        out.writeUTF(result);
        out.flush();
    }

    private String goToSubDirectory(DataInputStream in, DataOutputStream out) throws IOException {

        out.writeUTF("Введите имя каталога");
        out.flush();
        NAMESUBDIRECTORY = in.readUTF();

        String result = null;
        DIRECTORYNAME = File.separator + NAMESUBDIRECTORY;
        File file = new File(DIRECTORYNAME);
        for(File item : file.listFiles()){
            if(item.isDirectory()){
                result = result + item.getName() + "\r\n";
            }
        }
        return result;
    }

    private String goToParentDirectory(DataInputStream in, DataOutputStream out) throws IOException {

        File file = new File(DIRECTORYNAME.replace(NAMESUBDIRECTORY, ""));

        return file.getAbsolutePath();
    }

    private String downloadFile() {
        System.out.println("Скачали файл");
        return "Скачали файл";
    }

    private String uploadFile() {
        System.out.println("Загрузили файл");
        return "Загрузили файл";
    }

}
