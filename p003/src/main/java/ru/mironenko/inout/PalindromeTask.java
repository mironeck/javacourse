package ru.mironenko.inout;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The class with method that check a word is palindrome
 * Created by nikita on 06.12.2016.
 */
public class PalindromeTask {

    /**
     * The method ask user to enter a word and check it is palindrome
     * @return true if word is palindrome and return false if not
     */
    public static boolean isPalindrome(){
        boolean result = false;

        System.out.println("Enter a word with 5 letters :");
        Scanner scan = new Scanner(System.in);
        String word = scan.next();

        char[] symbols = word.toLowerCase().toCharArray();

        if (symbols.length != 5) {
            System.out.println("Error. You entered a wrong word ");
        } else {
            if ((symbols[0] == symbols[4]) && (symbols[1] == symbols[3])) {
                result = true;
            }
        }
        return result;
    }

}
