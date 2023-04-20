package com.hfj.sinkthestartup.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Startup {
	private Random randomizer = new Random();
	private String name;
	private List<String> cells = new ArrayList<>();
	private static Logger log = LoggerFactory.getLogger(Startup.class);

	public Startup(String name){
		setName(name);
	}

	public void randomizeCells() {
		//determine run along column or row
		int colOrRow = randomizer.nextInt(2);
		if(colOrRow == 0){
			//row based startup
			int randomRow = randomizer.nextInt(8);
			int randomColStart = randomizer.nextInt(6);
			char row = convertIntToRowName(randomRow);

			for(int i=0; i < 3; i++){
				String cell = (row + Integer.toString(randomColStart + i));
				setCell(cell);
			}
		} else {
			//column based startup
			int randomCol = randomizer.nextInt(8);
			int randomRowStart = randomizer.nextInt(5);
			char row1 = convertIntToRowName(randomRowStart);
			char row2 = convertIntToRowName(randomRowStart + 1);
			char row3 = convertIntToRowName(randomRowStart + 2);
		
			String cell1 = (row1 + Integer.toString(randomCol));
			String cell2 = (row2 + Integer.toString(randomCol));
			String cell3 = (row3 + Integer.toString(randomCol));
			setCell(cell1);
			setCell(cell2);
			setCell(cell3);

		}
		
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCell(String cell){
		cells.add(cell);
	}

	public void removeCell(String cell){
		cells.remove(cell);
	}

	public List<String> getCells(){
		return cells;
	}

	public void clearCells(){
		cells.clear();
	}

	public StringBuilder cellsToString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 3; i++){
			if(i < 2) str.append(String.format("%s + ", cells.get(i)));
			else str.append(cells.get(i));
		}
		return str;
	}

	public void displayCells(){
		log.info(name);
		for(String cell: cells){
			log.info(cell);
		}
	}

	public boolean containsCell(String cellToCheck){
		for(String cell : cells){
			if(cell.equals(cellToCheck)) return true;
		}
		return false;
	}

	public char convertIntToRowName(int num){
		switch(num){
			case 0: return 'A';
			case 1: return 'B';
			case 2: return 'C';
			case 3: return 'D';
			case 4: return 'E';
			case 5: return 'F';
			case 6: return 'G';
			default: return 'F';
		}
	}

	public String toString(){
		return "name: " + name + " | cells: " + cells;
	}

}
