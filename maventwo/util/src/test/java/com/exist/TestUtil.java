package com.exist;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import static org.mockito.Mockito.*;

public class TestUtil {

	

	List<String> rowList = new ArrayList<>();
	List<String> columnList = new ArrayList<>();
	

	@Test
	public void testGetMaxColumn() throws IOException {
		int expectedResult = 4;
		assertEquals(expectedResult, Utilities.getNumOfColumn());
	}

	@Test
	public void testGetMaxRow() throws IOException {
		int expectedResult = 6;
		assertEquals(expectedResult, Utilities.getNumOfRow());
	}

	@Test
	public void testReverse() {
		String expected = "fedcba";
		String reversed = Utilities.reverse("abcdef");
		assertEquals(expected, reversed);
		// assertTrue(expected.equals(Utilities.sort("abcdef")));
		
	}

	@Test
	public void testCheckOccurence() {
		String a = "aaa";
		String toBeSearched = "aaabcdef";
		int expectedResult = 1;
		assertEquals(expectedResult, Utilities.checkOccurence(toBeSearched, a));
	}
	
}