package ru.mironenko.codefromlesson;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by nikita on 09.03.2017.
 */
public class RandomAccessFileCodeExemple {

    // method to write data in file
    private static void writeData(String filePath, String data, int seek) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        //переходим на определённый индекс
        file.seek(seek);
        //пишем данные в этом месте
        file.write(data.getBytes());
        file.close();
    }

    // method to read data from file
    private static byte[] readData(String filePath, int seek, int size) throws IOException {

        //открываем файл только для чтения
        RandomAccessFile file =  new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[size];
        file.read(bytes);
        file.close();
        return bytes;
    }

    public static void main(String[] args) {

        try {
            String filePath = "D:\\input.txt";
            System.out.println(new String(readData(filePath, 0, 1)));
            writeData(filePath, "Data", 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
