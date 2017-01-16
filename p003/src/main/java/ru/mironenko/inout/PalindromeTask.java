package ru.mironenko.inout;

import java.util.Scanner;

//Создать программу, которая будет проверять, является ли слово из пяти букв,
// введённое пользователем, палиндромом (примеры: «комок», «ротор»). Если введено слово не из 5 букв,
// то сообщать об ошибке. Программа должна нормально обрабатывать слово,
// даже если в нём использованы символы разного регистра.
// Например, слова «Комок» или «РОТОР» следует также считать палиндромами.

/**
 * The class with method that check a word is palindrome
 * Created by nikita on 06.12.2016.
 */
public class PalindromeTask {

    /**
     * The method ask user to enter the word with odd letters and check it word.
     * @return this word, If word with even letter return "".
     */
    public String enterTheWord(){
        String result;
        System.out.println("Enter a word with odd number of letters :");
        try ( Scanner scan = new Scanner(System.in) ) {
            result = scan.next();
            char[] symbols = result.toCharArray();
            if ((symbols.length % 2) == 0) {
                System.out.println("Error. You entered a wrong word ");
                result = "" ;
            }
            return result;
        }
    }

    /**
     * The method check word is palindrome
     * @return true if word is palindrome and return false if not
     */
    public boolean isPalindrome(String word) {
        boolean result = false;
        char[] letters = word.toLowerCase().toCharArray();
        for (int i = 0; i < letters.length/2 - 1; i++) {
            if (letters[i] == letters[letters.length - i -1]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

}
