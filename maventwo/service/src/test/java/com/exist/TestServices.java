package com.exist.maventwo;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class TestServices {

	Services services = new Services();

	List<String> rowList = new ArrayList<>();
	List<String> columnList = new ArrayList<>();
	int row = 1;
	int column = 1;

	@Test
	public void setUp() {
		


	}

	@Test
	public void validatePrint() {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		assertTrue(services.printList(rowList, columnList, row, column));
	}

	@Test

	public void validateSearch() {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		String test = "a";
		assertTrue(services.search(test, row, column, rowList, columnList));

	}

	@Test

	public void validateCheckNull() {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		rowList.add(null);
		columnList.add(null);
		int expectedResult = 2;
		assertEquals(expectedResult, services.checkNull(rowList, columnList, 0));
	}

	@Test
	public void validateAddNewRow() throws IOException {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		rowList.add(null);
		columnList.add(null);
		int expectedResult = 3;
		assertEquals(expectedResult, services.addNewRow(row, column, rowList, columnList));
	}

	@Test
	public void validateConcatenateRowAndColumn() throws IOException {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		rowList.add(null);
		columnList.add(null);
		assertTrue(services.concatenateRow('a', 0, rowList, columnList, column, row));
	}

	@Test
	public void validateEdit() throws IOException {
		rowList.add("abc");
		rowList.add("ghi");
		columnList.add("def");
		columnList.add("jkl");
		rowList.add(null);
		columnList.add(null);
		int expectedResult = rowList.size();
		assertEquals(expectedResult, services.computeForRowAndColumnIndex(row, column, 1, 1, "abcd", "efg", rowList, columnList ));
	}
}