package main.java.ru.mironenko.srp;

import java.util.Scanner;

/**
 * Created by nikita on 16.02.2017.
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Method return users answer
     * @param question
     * @return
     */
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }
}
