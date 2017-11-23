package alphabet_check;
import java.util.Scanner;

/*
 * @author Matthew Sokolovsky (https://github.com/OlvynChuru)
 * 
 * This program takes in a string and finds
 *  all the distinct letters in the string.
 * It has methods for returning the string that
 *  shows the distinct letters, a count of the number
 *  of distinct letters, or whether or not it contains
 *  all the letters. It can also display all this information.
 * The main method prompts you to enter a string
 *  as the input, though you could also call one of
 *  the other methods by itself.
 */

public class Alphabet_Check {
	private final static int ALPHABET_LENGTH = 26;
	
	public static String findLetters(String text) {
		char select;
		char[] letter_list = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
		String lettersFound = "";
		text = text.toUpperCase();
		for (int i = 0; i < text.length(); i++)
		{
			select = text.charAt(i);
			if (select >= 'A' && select <= 'Z')
			{
				letter_list[select - 65] = select;
			}
		}
		for (int i = 0; i < ALPHABET_LENGTH; i++)
			lettersFound += letter_list[i];
		return lettersFound;
	}
	
	public static boolean allLettersFound(String lettersFound) {
		for (int i = 0; i < ALPHABET_LENGTH; i++)
			if (lettersFound.charAt(i) == ' ')
				return false;
		return true;
	}

	public static boolean allLettersFound_Find(String text) {
		String lettersFound = findLetters(text);
		return allLettersFound(lettersFound);
	}
	
	public static int countLettersFound(String lettersFound) {
		int letterCount = 0;
		for (int i = 0; i < ALPHABET_LENGTH; i++)
			if (lettersFound.charAt(i) != ' ')
				letterCount++;
		return letterCount;
	}
	
	public static int countLettersFound_Find(String text) {
		String lettersFound = findLetters(text);
		return countLettersFound(lettersFound);
	}
	
	public static String toString(String text) {
		String lettersFound = findLetters(text);
		return ("Letters found: " + lettersFound +
				"\nNumber of different letters: " + countLettersFound(lettersFound) +
				"\nAll letters found: " + (allLettersFound(lettersFound) ? "Yes" : "No"));
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Type some text: ");
		System.out.println(toString(scan.nextLine()));

	}

}
