package ru.mironenko.inout;

import java.io.*;
import java.util.*;

//1. Есть файл размер более 3G.
//2. Файл тестовый. В каждой строке записана строка - длинной n.
//3. Нужно реализовать интерфейс sort(File source, File distance);
//4. Необходимо отсортировать файл по возрастанию длин строк, использовать внешнюю сортировку и RandomAccessFile.

/**
 * Created by nikita on 14.11.2016.
 */
public class SortTask {


    public void sort(File source, File distance) throws IOException {

        try (
                FileReader fileReader = new FileReader(source);
                FileWriter fileWriter = new FileWriter(distance);
                BufferedReader bufferedReader = new BufferedReader(fileReader) )
                 {

            List<String> list = new ArrayList<>();
            //put lines in arrayList
            String line;
            while((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }

            list = sortLinesInArrayList(list);

            for(String liner : list) {
                fileWriter.write(liner + "\r\n");
            }
        }

    }

    /**
     *The method sorts lines ascending in arrayList
     * @param list
     *      arrayList for sort
     * @return return sorting list
     */
    private List<String> sortLinesInArrayList(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        return list;
    }


    //******************************************

    private String[] readLinesFromFileWriteItToArrayAndSort(RandomAccessFile raf) throws IOException {
        String[] result = new String[10];
        for(int i = 0; i < 10; i++) {
            result[i] = raf.readLine();
        }
        Arrays.sort(result);
        return result;
    }

    private void writeLinesFromArrayToTempFile(String[] array, RandomAccessFile raf) throws IOException {

        for (String line: array) {
            raf.write(line.getBytes());
        }
    }

    private void readLinesFromFileSortAndWriteToTwoTempFiles(RandomAccessFile srcFileRaf,
                                                             RandomAccessFile tmpFileOne,
                                                             RandomAccessFile tmoFileTwo) throws IOException {

        RandomAccessFile currentFile = tmpFileOne;

        while (srcFileRaf.getFilePointer() != srcFileRaf.length()) {

            String[] tmpArray = readLinesFromFileWriteItToArrayAndSort(currentFile);
            writeLinesFromArrayToTempFile(tmpArray, currentFile);

            if (currentFile == tmpFileOne) {
                currentFile = tmoFileTwo;
            } else {
                currentFile = tmpFileOne;
            }
        }
    }

    private void mergeFiles() {}

}
