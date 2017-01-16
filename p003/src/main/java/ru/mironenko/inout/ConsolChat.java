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
import java.util.Random;
import java.util.Scanner;

/**
 * The class simulates chat
 * Created by nikita on 11.01.2017.
 */
public class ConsolChat {

    private String stopAnswer = "стоп";
    private String continueAnswer = "продолжить";
    private String endChat = "закончить";

    private boolean isStoped = false;
    private boolean isEnded = false;

    private String logFile =  String.format("%s%s%s", System.getProperty("user.dir"),
        File.separator, "src/test/java/ru/mironenko/inout/temp/Log.txt");

    private String phrasesFile = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "src/test/java/ru/mironenko/inout/temp/Phrases.txt");

    private List<String> phrasesFromFileForAnswers = new ArrayList<>();

    public ConsolChat(){
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

                if(line.equals(endChat)) {
                    isEnded = true;
                }
                if(line.equals(stopAnswer)) {
                    isStoped = true;
                }
                if(line.equals(continueAnswer)) {
                    isStoped = false;
                }

                writer.write(line + "\r\n");

                if (!isStoped && !isEnded) {
                    writer.write(getRandomPhraseFromList() + "\r\n");
                }
            }
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
