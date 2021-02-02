package com.exist;


import java.util.*;
import java.io.*;
import org.apache.commons.lang3.*;

class Services {

	List<String> listForSearch = new ArrayList<>();
	List<Integer> lengthPerKey = new ArrayList<>();
	List<Integer> lengthPerValue = new ArrayList<>();


	public void clearList() {
		lengthPerValue.clear();
		lengthPerKey.clear();
	}

	public boolean printList(List<String> rowList, List<String> columnList, int row, int column) {
		boolean isValid = false;
		int counter = 0,  i = 0;
		while(i < row) {
			int j = 0;
			while(j < column) {
				if(rowList.get(counter) == null) {
					j++;
				}else {
					System.out.print(rowList.get(counter) + ":" + columnList.get(counter) + "\t");
					j++;
				}				
				counter++;
				isValid = true;
			}
			System.out.println();
			i++;
		}
		return isValid;
	}

	public boolean search(String userInput, int row, int column, List<String> rowList, List<String> columnList) {
		int rowCtr = 0, columnCtr = 0, counter = 0;
		boolean isValid = false;
		while(rowCtr < row) {
			columnCtr = 0;
			while(columnCtr < column) {
				String concatenated = rowList.get(counter) + columnList.get(counter);
				int count = Utilities.checkOccurence(concatenated, userInput);
				if(count > 0) {
					System.out.println(userInput + " has " + count + " instance(s) on index " + rowCtr +  " " + columnCtr);
					isValid = true;
				}
				columnCtr++;
				counter++;
			}
			rowCtr++;
		}
		return isValid;
	}

	public int computeForRowAndColumnIndex(int row, int column, int rowToEdit, int columnToEdit, String newKey, String newValue, List<String> rowList, List<String> columnList) throws IOException {
		int multiplier = Utilities.getMultiplier(rowToEdit);
		int difference = column - columnToEdit;
		int totalSize = rowList.size(); int countSizeAfterEdit;
		if(rowToEdit > 0) {
			columnToEdit = (multiplier * column) + columnToEdit;
			rowToEdit = columnToEdit;
			insertToList(rowToEdit, columnToEdit, newKey, newValue, rowList, columnList, row, column);
			countSizeAfterEdit = rowList.size() - 1;
		} else if ((rowToEdit > 0) && (columnToEdit < column)) {
			columnToEdit = (multiplier * column) + difference;
			rowToEdit = columnToEdit;
			insertToList(rowToEdit, columnToEdit, newKey, newValue, rowList, columnList, row, column);
			countSizeAfterEdit = rowList.size() - 1;
		} else {
			rowToEdit = columnToEdit;
			insertToList(rowToEdit, columnToEdit, newKey, newValue, rowList, columnList, row, column);
			countSizeAfterEdit = rowList.size() - 1;
		}
		return countSizeAfterEdit - 1;

	}

	public void insertToList(int rowToEdit, int columnToEdit, String newKey, String newValue, List<String> rowList, List<String> columnList, int row, int column) throws IOException {
		
		if((!newKey.isEmpty()) && (newValue.isEmpty())) {
			rowList.set(rowToEdit, newKey);
		} else if ((StringUtils.isEmpty(newKey)) && (StringUtils.isNotEmpty(newValue))) {
			columnList.set(columnToEdit, newValue);;
		} else {
			rowList.set(rowToEdit, newKey);
			columnList.set(columnToEdit, newValue);
		}

		ReadWrite.writeToFile(rowList, columnList, row, column);
	}

	public int addNewRow(int row, int column, List<String> rowList, List<String> columnList) throws IOException {
		int rowBasedZero = row; int counter = 0;
		row = row + 1;
		// while(counter < column) {
		// 	String question = "Enter key for " + rowBasedZero + "," + counter +" : ";
		// 	String newKey = Utilities.getStringForInput(question);
		// 	question = "Enter value for " +  rowBasedZero + "," + counter + " : ";
		// 	String newValue = Utilities.getStringForInput(question);
		// 	rowList.add(newKey);
		// 	columnList.add(newValue);
		// 	counter++;
		// }
		int result = row + column;

		ReadWrite.writeToFile(rowList, columnList, row, column);
		return result;
	}

	public int checkNull(List<String> rowList, List<String> columnList, int index) {
		int counter = 0;
		while(rowList.get(index) != null) {
			counter++;
			index++;
		}
		return counter;
	}

	public boolean concatenateRow(char input, int rowToSort, List<String> rowList, List<String> columnList, int column, int row) throws IOException {
		boolean isValid = false;
		int multiplier = Utilities.getMultiplier(rowToSort);
		int index = Utilities.computeForIndex(rowToSort, multiplier, column);
		int columnCtr = 0; String concat = "";
		int test = checkNull(rowList, columnList, index);
		if(test > 0) {
			while(columnCtr < test) {			
				String temp1 = rowList.get(index);
				String temp2 = columnList.get(index);
				lengthPerKey.add(temp1.length());
				lengthPerValue.add(temp2.length());
				concat += (temp1 + temp2);				
				 columnCtr++; index++;
			}		
		} else {
			while(columnCtr < column) {
				String temp1 = rowList.get(index);
				String temp2 = columnList.get(index);
				lengthPerKey.add(temp1.length());
				lengthPerValue.add(temp2.length());
				concat += (temp1 + temp2);
				columnCtr++; index++;				
			}
		}
		if(input == 'A') {
			String toBeSorted = Utilities.sort(concat);
			subStringSorted(toBeSorted, rowToSort, multiplier, row, column, rowList, columnList);
			System.out.println(toBeSorted);
			isValid = true;
		} else {
			String toBeSorted = Utilities.sort(concat);
			String reversed = Utilities.reverse(toBeSorted);
			subStringSorted(reversed, rowToSort, multiplier, row, column, rowList, columnList);
			isValid = true;
		}
		return isValid;
			

	}

	private void subStringSorted(String sorted, int rowToBeSorted, int multiplier, int row, int column, List<String> rowList, List<String> columnList) throws IOException {
		int columnCtr = 0, startKey = 0, endKey = 0, startValue = 0, endValue = 0;
		int index = Utilities.computeForIndex(rowToBeSorted, multiplier, column);
		int test = checkNull(rowList, columnList, index);
		if(test > 0) {
			while(columnCtr < test) {
				endKey = endKey + lengthPerKey.get(columnCtr);
				String temp = sorted.substring(startKey, endKey);
				rowList.set(index, temp);
				startValue = endKey;
				endValue = startValue + lengthPerValue.get(columnCtr);
				String temp2 = sorted.substring(startValue, endValue);
				columnList.set(index, temp2);
				startKey = endValue;
				endKey = startKey;
				columnCtr++;
					index++;
			}
		}else {
			while(columnCtr < column) {
				endKey = endKey + lengthPerKey.get(columnCtr);
				String temp = sorted.substring(startKey, endKey);
				rowList.set(index, temp);
				startValue = endKey;
				endValue = startValue + lengthPerValue.get(columnCtr);
				String temp2 = sorted.substring(startValue, endValue);
				columnList.set(index, temp2);
				startKey = endValue;
				endKey = startKey;
				columnCtr++;
				index++;
			}
		}		
		ReadWrite.writeToFile(rowList, columnList, row, column);
	}

}