package com.csg;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WordCountTest {
	private final String inputFile = "src/main/resources/CSVDemo.csv";
	WordCount wc;
	List<String> resultList;
	char charStartFilterInput;
	int lengthFilterInput;
	char customInput;

	@Before
	public void setup() {
		wc = new WordCount();
		resultList = new LinkedList<>();
	}

	@Test
	public void countAndPrintWordsTest1() {
		charStartFilterInput = 'a';
		lengthFilterInput = 9;
		boolean result = false;

		try {
			result = wc.countAndPrintWords(inputFile, charStartFilterInput, lengthFilterInput);
		} catch (FileNotFoundException e) {
			System.out.println("::Exception occurred while processing the file::=>" + e.getMessage());
		}
		assertEquals(true, result);
	}

}
