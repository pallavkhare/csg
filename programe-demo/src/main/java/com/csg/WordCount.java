package com.csg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;

public class WordCount {
	static int count = 0;
	List<String> resultList = new LinkedList<>();
	static char charStartFilterInput;
	static int lengthFilterInput;
	static char customInput;

	public static void main(String[] args) throws Exception {
		// parsing a CSV file into Scanner class constructor

		String inputFile = "src/main/resources/CSVDemo.csv";

		WordCount wc = new WordCount();
		getInput();
		wc.countAndPrintWords(inputFile, charStartFilterInput, lengthFilterInput);
	}

	private static void getInput() {
		Scanner sc1 = new Scanner(System.in);

		System.out.println(
				"Default criteria of the program is : fetch the number of words start with \"M\" or \"m\" & fetch  all the words which are longer than \"5\" characters");
		System.out.println("press \"N\"or \"n\" to give custom inputs or press any other key to continue...");

		customInput = sc1.next().charAt(0);

		if (Character.toString(customInput).equalsIgnoreCase("N")) {
			System.out.println("Enter the character to identify the count of words which start with given input =>");
			charStartFilterInput = sc1.next().charAt(0);
			System.out.println("You have pressed =>" + charStartFilterInput + " to choose a custom input option");
			lengthFilterInput = validatePositiveNumber();
		} else {
			System.out.println("You just opt a default option character \"M\" or \"m\" & length more than 5");
			charStartFilterInput = 'M';
			lengthFilterInput = 5;
		}
	}

	boolean countAndPrintWords(String inputFilePath, char charStartFilterInput, int lengthFilterInput)
			throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(inputFilePath))) {
			sc.useDelimiter(","); // sets the delimiter pattern
			while (sc.hasNext()) // returns a boolean value
			{
				String str = sc.next();
				char ch = str.charAt(0);
				wordStartwith(ch, charStartFilterInput);
				wordLongerThan(str, lengthFilterInput);
				CollectionUtils.addIgnoreNull(resultList, wordLongerThan(str, lengthFilterInput));
			}
			System.out.println("Number of word start with char " + charStartFilterInput + " are => " + count);

			System.out.println("String which are having length more than " + lengthFilterInput + " are => "
					+ resultList.toString());			
		}
		return true;
	}

	private int wordStartwith(char ch, char charStartFilterInput) {
		if (Character.toString(ch).equalsIgnoreCase(Character.toString(charStartFilterInput))) {
			count++;
		}
		return count;
	}

	private String wordLongerThan(String str, int lengthFilterInput) {

		if (str.length() > lengthFilterInput) {
			return str;
		}
		return null;
	}

	private static int validatePositiveNumber() {
		Scanner scanner = new Scanner(System.in);

		int number;
		do {
			System.out.print("Please enter a positive number to filter out the string based on the given input: ");
			while (!scanner.hasNextInt()) {
				String input = scanner.next();
				System.out.printf("\"%s\" is not a valid number.%n", input + " Please try again!!!");
			}
			number = scanner.nextInt();
		} while (number < 0);

		System.out.printf("You have entered a positive number %d.%n", number);
		scanner.close();
		return number;
	}
}
