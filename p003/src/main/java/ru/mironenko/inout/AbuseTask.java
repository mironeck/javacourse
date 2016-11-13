package ru.mironenko.inout;

import java.io.*;


/**
 * Created by nikita on 02.11.2016.
 */
/*
* Реализовать сервис
*void dropAbuses(InputStream in, OutputStream out, String[] abuse)
*задан символьный входной поток и символьный выходной поток. Надо удалить все слова abuse [].
*Важно: все преобразования нужно делать в потоке(нельзя cчитать весь поток в память,
* удалить слова и потом записать).
* */

/**
 * The class with method to remove words from input stream and pull to output stream without them
 */

public class AbuseTask {

    /**
     * The method to remove words from input stream and pull to output stream without them
     * @param in input stream
     * @param out output stream
     * @param abuse String array with words to remove
     * @throws IOException
     */
    public static void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        String word;

        //считываем линию
        while ((word = reader.readLine()) != null) {

            //проходим по массиву запрещённый слов, и удаляем если таковое имеется
            for (String str : abuse) {
                    word = removeWords(word, str);
            }
            writer.write(word);
        }

        reader.close();
        writer.close();
    }

    /**
     * The method to remove word from sentence
     * @param message input sentence
     * @param wordToRemove word to remove
     * @return sentence without word to remove
     */
    public static String removeWords(String message ,String wordToRemove) {
        return message.replace(wordToRemove,"");
    }
}
