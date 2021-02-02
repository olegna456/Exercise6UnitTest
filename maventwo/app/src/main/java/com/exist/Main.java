package com.exist;


import java.io.*;
import java.util.*;

class Main {

	static String fileName;
	static Services srv = new Services();

	public static void main(String[] args) throws IOException  {	

		// List<String>
		
		int row = Utilities.getNumOfRow();
		int column = Utilities.getNumOfColumn();
		ArrayList<String> list = ReadWrite.readFile(row, column);
		ClassList cl = new ClassList(list, row, column);
		List<String> rl = cl.getRowList();
		List<String> colL = cl.getColumnList();
		
		srv.printList(rl, colL, row, column);
		while(true) {
			update();
			selectOption(rl, colL, row, column);
		}
	}

	static void update() throws IOException {
		srv.clearList();
		ClassList cl = new ClassList();
		int row = Utilities.getNumOfRow();
		int column = Utilities.getNumOfColumn();
		ArrayList<String> list = ReadWrite.readFile(row, column);
		ClassList clear = new ClassList(list, row, column);
		List<String> rl = cl.getRowList();
		List<String> colL = cl.getColumnList();

	}

	static void selectOption(List<String> rl, List<String> colL, int row, int column) throws IOException {
		showOptions();
		int min = 1;
		int max = 7;
		String question = "Enter your choice: ";
		String error = "Invalid Input. Please Choose from " + min + "-" + max + " only.";
		int choice = Utilities.getChoice(min, max, question, error);
		userInput(choice, rl, colL, row, column);

	}

	static void showOptions() {		
		System.out.println("Select an Option");
		System.out.println("1. Print");
		System.out.println("2. Search");
		System.out.println("3. Edit");
		System.out.println("4. Add New Row");
		System.out.println("5. Sort");
		System.out.println("6. Reset");
		System.out.println("7. Exit");	
	}

	static void userInput(int choice, List<String> rl, List<String> colL, int row, int column) throws IOException {
		switch(choice) {
			case 1:
				srv.printList(rl, colL, row, column);
				break;
			case 2:
				String question = "Enter string to be searched: ";
				String userInput = Utilities.getStringForInput(question);
				// srv.concatenateForSearch(rl, colL, row, column);
				srv.search(userInput, row, column, rl, colL);
				break;
			case 3:
				int min = 0;
				int max = row - 1;
				question = "Enter row to edit: ";
				String error = "Invalid Input.  max  row xis only " + max + "." ;
				int userRowToEdit = Utilities.getChoice(min , max, question, error);
				max = column - 1;
				question = "Enter column to edit: ";
				error = "Invalid Input.  max of column is only " + max + "."; 
				int userColToEdit = Utilities.getChoice(min, max, question, error);

				question = "What do you want to edit? \n a. key \n b. value \n c. key and value \n Enter choice: ";
				userInput = Utilities.getStringForInput(question);
				char charInput = Utilities.checkIfRowColOrRowAndCol(userInput);
				String newKey = "";
				String newValue = "";
				if(charInput == 'a') {
					String question1 = "Enter key to be inserted to " + userRowToEdit + "," + userColToEdit+": ";
					newKey = Utilities.getStringForInput(question1);
					srv.computeForRowAndColumnIndex(row, column, userRowToEdit, userColToEdit, newKey, newValue, rl, colL);
				} else if(charInput == 'b') {
					question = "Enter value to be inserted to " + userRowToEdit + "," + userColToEdit+": ";
					newValue = Utilities.getStringForInput(question);
					srv.computeForRowAndColumnIndex(row, column, userRowToEdit, userColToEdit, newKey, newValue, rl, colL);
				} else {
					String question1 = "Enter key to be inserted to " + userRowToEdit + "," + userColToEdit+": ";
					newKey = Utilities.getStringForInput(question1);
					question = "Enter value to be inserted to " + userRowToEdit + "," + userColToEdit +": ";
					newValue = Utilities.getStringForInput(question);
					srv.computeForRowAndColumnIndex(row, column, userRowToEdit, userColToEdit, newKey, newValue, rl, colL);
				}					
				break;
			case 4:
				srv.addNewRow(row, column, rl, colL);
				break;
			case 5:
				question = "Enter row to sort(base 0): ";
				min = 0;
				max = row - 1;
				error = "Invalid Input please enter a valid input";
				int rowToSort = Utilities.getChoice(min, max, question, error);
				question = "How do you want it to be sorted: \n A. Ascending \n B. Descending \n Enter your choice: ";
				String descOrAsc = Utilities.getStringForInput(question);
				char input = descOrAsc.toUpperCase().charAt(0);
				if((input == 'A') || ((input == 'B'))) {
					srv.concatenateRow(input, rowToSort, rl, colL, column, row);
				}
				break;
			case 6:
				break;
			case 7:
				System.exit(1);
				break;
		} 
	}
}