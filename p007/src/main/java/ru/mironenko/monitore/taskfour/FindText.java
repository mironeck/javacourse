package ru.mironenko.monitore.taskfour;

import java.io.File;

/**
 * Created by nikita on 05.06.2017.
 */
//1. Нужно осуществлять обход файловой системы и поиск заданного текста в файловой системе.
// Приложение многопоточное, запускается из командной строки и принимает аргумент как параметр поиска.
// 2. Предусмотреть возможность останавливать поиск. если хотя бы один тред нашел совпадение.
// сделать это через флаг запуска.

public class FindText {

    private File rootFile = new File("C:\\Tools");

    public synchronized void findTextInFileSystem(String text) {


        //while(!Thread.currentThread().isInterrupted()) {

            File[] list = rootFile.listFiles();
            if(list != null) {

                for(File tmp : list) {

                    if(tmp.isDirectory()) {
                        findTextInFileSystem(text);
                    } else if(tmp.getName().contains(text)) {
                        System.out.format("Text match founded in %s", tmp.getName());
                        Thread.currentThread().interrupt();
                    }
                }
            }

       // }
    }

    public static void main(String[] args) {

        //args[0] = "Tools";

        FindText findText = new FindText();
        findText.findTextInFileSystem("bin");
    }

}
