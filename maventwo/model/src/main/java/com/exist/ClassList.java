package com.exist.maventwo;

import java.util.*;

class ClassList {

	private static List<String> rowList = new ArrayList<>();
	private static List<String> columnList = new ArrayList<>();
	private static int row;
	private static int column;

	ClassList(ArrayList<String> concatenatedList, int row, int column) {
		splitConcatenatedList(concatenatedList);
		setRow(row);
		setColumn(column);
	}

	ClassList() {
		rowList.clear();
		columnList.clear();
	}

	public void clearList() {
		rowList.clear();
		columnList.clear();
	}

	private void splitConcatenatedList(ArrayList<String> concatenatedList) {
		for(String values : concatenatedList) {
			String[] content = values.split("\u00BD");
			setRowList(content[0]);
			setColumnList(content[1]);
		}
	}
	
	private void setRowList(String rowValue) {
		if(rowValue.equals("null")) {
			rowList.add(null);
		}else {
			rowList.add(rowValue);
		}
		
	}

	private void setColumnList(String columnValue) {
		if(columnValue.equals("null")) {
			columnList.add(null);
		} else {
			columnList.add(columnValue);
		}
		
	}

	private void setRow(int row) {
		this.row = row;
	}

	private void setColumn(int column) {
		this.column = column;
	}

	public List<String> getRowList() {
		return rowList;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}


}