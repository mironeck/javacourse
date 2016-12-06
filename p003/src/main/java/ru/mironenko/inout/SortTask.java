package ru.mironenko.inout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//1. Есть файл размер более 3G.
//2. Файл тестовый. В каждой строке записана строка - длинной n.
//3. Нужно реализовать интерфейс sort(File source, File distance);
//4. Необходимо отсортировать файл по возрастанию длин строк, использовать внешнюю сортировку и RandomAccessFile.

/**
 * Created by nikita on 14.11.2016.
 */
public class SortTask {

    public static void sort(File source, File distance) throws FileNotFoundException {

        try ( RandomAccessFile soursFile = new RandomAccessFile(source.getPath(), "r");
        RandomAccessFile distanceFile = new RandomAccessFile(distance.getPath(), "w") )
        {





        }  catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void splitFile(File sourse){

    }

    private static void mergFiles(File firstFileToMerge, File secondFileToMerge){

    }
}
