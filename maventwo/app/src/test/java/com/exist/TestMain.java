package com.exist;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import static org.mockito.Mockito.*;

public class TestMain {

	Services services = new Services();

	List<String> rowList = new ArrayList<>();
	List<String> columnList = new ArrayList<>();
	int row = 1;
	int column = 1;

	@Test
	public void testSelectOption() {
		verify(selectOption(),times(1)).selectOption(rowList, columnList, row, column);
	}
	
}