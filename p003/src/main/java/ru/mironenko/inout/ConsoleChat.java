package ru.mironenko.inout;


//Создать программу консольный чат. Пользователь вводит слово-фразу,
// программа берет случайную фразу из текстового файла и выводит в ответ.
// Программа замолкает если пользователь вводит слово «стоп» при этом он может
// продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
// программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу.
// Запись диалога, включая слова-команды стоп/продолжить/закончить записать в текстовый лог.
// Так делать не надо. while (true) {} - консольный чат должен явно выходить из цикла. Не делайте вечный цикл.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * The class simulates chat
 * Created by nikita on 11.01.2017.
 */
public class ConsoleChat {

    private static final String STOP_ANSWER = "стоп";
    private static final String CONTINUE_ANSWER = "продолжить";
    private static final String END_CHAT = "закончить";

    private boolean isStopped = false;
    private boolean isEnded = false;

    private String logFile;
    private String phrasesFile;


    private List<String> phrasesFromFileForAnswers = new ArrayList<>();

    public ConsoleChat(String phrasesFile, String logFile){
        this.phrasesFile = phrasesFile;
        this.logFile = logFile;
    }

    /**
     * The method waits enter from user in console and answers him with random phrase
     * @throws IOException
     */
    public void isChatting() throws IOException{

        putPhrasesFromFileToArrayList();

        try( Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter(this.logFile)
              )
        {
            while(!isEnded && (scanner.hasNext())) {
                String line = scanner.nextLine();
                equalPhraseWithKeyWords(line);
                writer.write(line + "\r\n");

                if (!isStopped && !isEnded) {
                    writer.write(getRandomPhraseFromList() + "\r\n");
                }
            }
        }
    }

    /**
     * The method equals phrase with key words and changes flags if it needs
     * @param line
     */
    private void equalPhraseWithKeyWords(String line) {
        if(END_CHAT.equals(line)) {
            isEnded = true;
        }
        if(STOP_ANSWER.equals(line)) {
            isStopped = true;
        }
        if(CONTINUE_ANSWER.equals(line)) {
            isStopped = false;
        }
    }

    /**
     * The method puts phrases from file to arraylist
     * @throws IOException
     */
    private void putPhrasesFromFileToArrayList() throws IOException {

        try ( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.phrasesFile), "windows-1251")) ) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                phrasesFromFileForAnswers.add(line);
            }
        }
    }

    /**
     * The method gets random phrase from list to answer
     * @return
     */
    private String getRandomPhraseFromList() {
        int lineIndex = (int) (Math.random() * phrasesFromFileForAnswers.size());
        String line = this.phrasesFromFileForAnswers.get(lineIndex);
        System.out.println(line);
        return line;
    }


}
